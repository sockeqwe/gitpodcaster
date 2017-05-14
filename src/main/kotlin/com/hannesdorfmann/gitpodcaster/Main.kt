package com.hannesdorfmann.gitpodcaster

import okio.BufferedSink
import okio.Okio
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer
import org.reflections.Reflections
import org.reflections.scanners.SubTypesScanner
import org.reflections.util.ClasspathHelper
import org.reflections.util.ConfigurationBuilder
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


fun main(args: Array<String>) {
    val reflections = Reflections(
            ConfigurationBuilder()
                    .addUrls(ClasspathHelper.forPackage(""))
                    .setScanners(SubTypesScanner())
    )
    val episodes = reflections.getSubTypesOf(Episode::class.java)
            .map { it.newInstance() }
            .sortedBy { it.releaseDate }


    val file = File("feed.rss")
    val sink = Okio.buffer(Okio.sink(file))
    val now = LocalDateTime.now(ZoneId.of("America/New_York"))
    writeRssFeed(sink, now, Podcast.hosts, episodes)

    val pullRequestId = System.getenv("TRAVIS_PULL_REQUEST")
    println("Pull Request Id: $pullRequestId")
    
    val authToken = System.getenv("GH_TOKEN")
    pullRequestId?.apply {
        postShowNodePreviewBackToPullRequest(pullRequestId)
    }
}


fun writeRssFeed(unsafeSink: BufferedSink, now: LocalDateTime, hosts: List<Person>, episodes: List<Episode>) {

    unsafeSink.use { sink ->

        val estFormatter = DateTimeFormatter.ofPattern("EEE, dd MMMM yyyy hh:mm:ss Z")


        sink.writeUtf8("""<?xml version="1.0" encoding="utf-8"?>""").writeUtf8("\n")
                .writeUtf8("""<rss xmlns:itunes="http://www.itunes.com/dtds/podcast-1.0.dtd" xmlns:atom="http://www.w3.org/2005/Atom" version="2.0">""").writeUtf8("\n")
                .writeUtf8("<channel>\n")
                .writeUtf8("\t<title>${Podcast.title}</title>\n")
                .writeUtf8("\t<link>${Podcast.link}</link>\n")
                .writeUtf8("\t<description>${Podcast.description}</description>\n")
                .writeUtf8("\t<language>${Podcast.language}</language>\n")
                .writeUtf8("\t<lastBuildDate>${now}</lastBuildDate>\n")
                .writeUtf8("\t<itunes:owner>\n")
                .writeUtf8("\t\t<itunes:email>artem.zinnatullin@gmail.com</itunes:email>\n")
                .writeUtf8("\t\t<itunes:name>Artem Zinnatullin</itunes:name>\n")
                .writeUtf8("\t</itunes:owner>\n")

        hosts.forEach { host ->
            sink.writeUtf8("\t<atom:author>\n")
                    .writeUtf8("\t\t<atom:name>${host.name}</atom:name>\n")

            host.website?.apply { sink.writeUtf8("\t\t<atom:uri>${host.website}</atom:uri>\n") }
            host.email?.apply { sink.writeUtf8("\t\t<atom:email>${host.email}</atom:email>\n") }

            sink.writeUtf8("\t</atom:author>\n")
        }

        sink.writeUtf8("\t<itunes:author>${Podcast.itunesAuthor.name}</itunes:author>\n")
                .writeUtf8("\t<itunes:explicit>no</itunes:explicit>\n")
                .writeUtf8("\t<itunes:keywords>${Podcast.keywords}</itunes:keywords>\n")
                .writeUtf8("\t<itunes:subtitle>${Podcast.subtitle}</itunes:subtitle>\n")
                .writeUtf8("\t<itunes:category text=\"Technology\">\n")
                .writeUtf8("\t\t<itunes:category text=\"Software How-To\"/>\n")
                .writeUtf8("\t</itunes:category>\n")
                .writeUtf8("\t<itunes:image href=\"${Podcast.imageUrl}\"/>\n")



        episodes.forEach { episode ->

            val markdownParser = Parser.builder().build()
            val renderer = HtmlRenderer.builder().build()
            val document = markdownParser.parse(episode.showNotes)

            val showNotesDir = Files.createDirectories(Paths.get("show_notes/"))
            val showNotesFile = File("$showNotesDir/${episode.showNotesFileName()}")
            Okio.buffer(Okio.sink(showNotesFile)).use {
                it.writeUtf8(episode.showNotes).flush()
            }

            sink.writeUtf8("\t<item>\n")
                    .writeUtf8("\t\t<title>${episode.title}</title>\n")
                    .writeUtf8("\t\t<<pubDate>${episode.releaseDate}</<pubDate>\n")
                    .writeUtf8("\t\t<itunes:summary>\n")
                    .writeUtf8("\t\t\t<![CDATA[\n")
                    .writeUtf8(renderer.render(document))
                    .writeUtf8("\t\t\t]]>\n")
                    .writeUtf8("\t</item>\n")
        }


        sink.writeUtf8("</channel>\n")
                .writeUtf8("</rss>")
                .flush()
    }
}

fun postShowNodePreviewBackToPullRequest(pullRequestId : String) {


}