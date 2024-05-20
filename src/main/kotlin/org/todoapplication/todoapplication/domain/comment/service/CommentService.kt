package org.todoapplication.todoapplication.domain.comment.service

import org.todoapplication.todoapplication.domain.comment.dto.CommentResponse
import org.todoapplication.todoapplication.domain.comment.dto.CommentRequest

interface CommentService {
    fun getComment(commentId : Long) : CommentResponse

    fun createComment(userId: Long, request: CommentRequest) : CommentResponse

    fun updateComment(userId: Long, commentId: Long, request: CommentRequest) : CommentResponse

    fun deleteComment(userId: Long, commentId: Long)
}