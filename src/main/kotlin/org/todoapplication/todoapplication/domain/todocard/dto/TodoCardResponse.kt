package org.todoapplication.todoapplication.domain.todocard.dto

import java.time.LocalDateTime

data class TodoCardResponse(
    val userid : Long,
    val writer : String,
    val title: String,
    val content : String,
    val date : LocalDateTime
)
