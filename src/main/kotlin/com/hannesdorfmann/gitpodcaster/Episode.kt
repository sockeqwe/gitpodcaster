package com.hannesdorfmann.gitpodcaster

import java.time.LocalDateTime

/**
 * Created by hannes on 13.05.17.
 */
open class Episode(
        val title: String,
        val showNotes: String,
        val releaseDate: LocalDateTime
)


fun Episode.showNotesFileName() = title.replace(" ", "_").replace(":", "_").replace("@", "at")+".md"