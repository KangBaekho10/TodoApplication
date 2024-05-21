package org.todoapplication.todoapplication.domain.comment.dto

data class DeleteCommentRequest (
    val writer: String,
    val password: String,
)