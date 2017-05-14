package com.hannesdorfmann.gitpodcaster

import java.net.URL

/**
 * Created by hannes on 13.05.17.
 */
data class Person(
        val name: String,
        val email: String ? = null,
        val twitter: String? = null,
        val website: String? = null,
        val github : String? = null
)