package org.todoapplication.todoapplication.domain.comment.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.todoapplication.todoapplication.domain.comment.dto.CommentResponse
import org.todoapplication.todoapplication.domain.comment.dto.CommentRequest
import org.todoapplication.todoapplication.domain.comment.dto.DeleteCommentRequest
import org.todoapplication.todoapplication.domain.comment.model.Comment
import org.todoapplication.todoapplication.domain.comment.model.toResponse
import org.todoapplication.todoapplication.domain.comment.repository.CommentRepository
import org.todoapplication.todoapplication.domain.exception.ModelNotFoundException
import org.todoapplication.todoapplication.domain.todocard.repository.TodoCardRepository

@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val todoCardRepository: TodoCardRepository
) : CommentService {
    override fun getComment(commentId: Long): CommentResponse {
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)
        return comment.toResponse()
    }

    @Transactional
    override fun createComment(userId: Long, request: CommentRequest): CommentResponse {
        val todoCard = todoCardRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("TodoCard", userId)
        return commentRepository.save(
            Comment(
                writer = request.writer,
                content = request.content,
                password = request.password,
                todoCard = todoCard,
            )
        ).toResponse()
    }

    @Transactional
    override fun updateComment(userId: Long, commentId: Long, request: CommentRequest): CommentResponse {
        val comment = commentRepository.findByTodoCardUseridAndCommentid(userId, commentId) ?: throw ModelNotFoundException("Comment", commentId)

        if (comment.writer == request.writer && comment.password == request.password) {

            comment.content = request.content

            return commentRepository.save(comment).toResponse()
        } else {
            throw IllegalArgumentException("작성 이름 또는 비밀번호가 일치하지 않습니다.")
        }
    }

    @Transactional
    override fun deleteComment(userId: Long, commentId: Long, request: DeleteCommentRequest) {
        val comment = commentRepository.findByTodoCardUseridAndCommentid(userId, commentId) ?: throw ModelNotFoundException("Comment", commentId)
        if (comment.writer == request.writer && comment.password == request.password) {
        return commentRepository.delete(comment)
            }
    }
}