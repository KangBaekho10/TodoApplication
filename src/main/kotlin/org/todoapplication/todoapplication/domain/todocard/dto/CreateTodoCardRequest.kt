package org.todoapplication.todoapplication.domain.todocard.dto

import java.time.LocalDateTime

data class CreateTodoCardRequest(
    val writer : String,
    val title: String,
    val content : String,
    val date : LocalDateTime
)
