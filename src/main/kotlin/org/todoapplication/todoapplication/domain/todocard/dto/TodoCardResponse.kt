package org.todoapplication.todoapplication.domain.todocard.dto

import org.todoapplication.todoapplication.domain.comment.dto.CommentResponse
import org.todoapplication.todoapplication.domain.todocard.model.TodoCardCompleted
import java.time.LocalDateTime

data class TodoCardResponse(
    val userId : Long,
    val writer : String,
    val title: String,
    val content : String,
    val date : LocalDateTime,
    val completed : TodoCardCompleted,
    val comments: List<CommentResponse>,
)