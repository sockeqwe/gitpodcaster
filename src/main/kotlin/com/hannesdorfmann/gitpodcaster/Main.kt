package com.hannesdorfmann.gitpodcaster

import org.reflections.Reflections
import org.reflections.scanners.SubTypesScanner
import org.reflections.util.ClasspathHelper
import org.reflections.util.ConfigurationBuilder
import java.nio.file.Files
import java.nio.file.Paths


fun main(args: Array<String>) {
    val reflections = Reflections(
            ConfigurationBuilder()
                    .addUrls(ClasspathHelper.forPackage(""))
                    .setScanners(SubTypesScanner())
    )
    val episodes = reflections.getSubTypesOf(Episode::class.java)

    episodes.forEach {
        println(it.newInstance())
    }

}