package org.sleepapp.data.model

import kotlinx.datetime.LocalDateTime

data class Note(
    val id:Long = 0,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
    )