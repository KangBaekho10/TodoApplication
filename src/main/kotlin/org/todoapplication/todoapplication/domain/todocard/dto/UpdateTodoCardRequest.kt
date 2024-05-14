package org.todoapplication.todoapplication.domain.todocard.dto

data class UpdateTodoCardRequest(
    val writer: String,
    val title: String,
    val content: String,
)
