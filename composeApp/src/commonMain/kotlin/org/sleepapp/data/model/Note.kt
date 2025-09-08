package org.sleepapp.data.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Note(
    @SerialName("id")
    val id:Long = 0,
    @SerialName("title")
    val title: String,
    @SerialName("content")
    val content: String,
    @SerialName("end_time")
    val createdAt: LocalDateTime,

    )