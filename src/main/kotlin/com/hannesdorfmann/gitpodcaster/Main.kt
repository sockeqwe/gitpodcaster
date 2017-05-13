package com.hannesdorfmann.gitpodcaster

import org.reflections.Reflections
import org.reflections.scanners.SubTypesScanner
import org.reflections.util.ClasspathHelper
import org.reflections.util.ConfigurationBuilder


fun main(args: Array<String>) {
    val reflections = Reflections(
            ConfigurationBuilder()
                    .addUrls(ClasspathHelper.forPackage(""))
                    .setScanners(SubTypesScanner())
    )
    val episodes = reflections.getSubTypesOf(Episode::class.java)

    episodes.forEach {
        println(it)
        it.constructors.forEach(::println)

        println(it.newInstance())

    }
}