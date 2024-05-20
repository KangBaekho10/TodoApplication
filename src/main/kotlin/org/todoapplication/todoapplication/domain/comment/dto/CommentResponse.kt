package org.todoapplication.todoapplication.domain.comment.dto

data class CommentResponse (
    val commentId: Long,
    val writer: String,
    val content: String,
    )