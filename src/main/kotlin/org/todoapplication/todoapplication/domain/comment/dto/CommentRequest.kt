package org.todoapplication.todoapplication.domain.comment.dto

data class CommentRequest(
    val writer: String,
    val content: String,
    val password: String,
)
