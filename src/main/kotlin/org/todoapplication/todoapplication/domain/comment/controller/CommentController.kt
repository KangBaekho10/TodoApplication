package org.todoapplication.todoapplication.domain.comment.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.todoapplication.todoapplication.domain.comment.dto.CommentRequest
import org.todoapplication.todoapplication.domain.comment.dto.CommentResponse
import org.todoapplication.todoapplication.domain.comment.service.CommentService

@RequestMapping("/todocards/{userId}")
@RestController
class CommentController(
    private val commentService: CommentService,
) {
    @PostMapping("/comments")
    fun createComment(
        @PathVariable userId: Long,
        @RequestBody commentRequest: CommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.createComment(userId, commentRequest))
    } // 생성

    @PutMapping("/comments/{commentId}")
    fun updateComment(
        @PathVariable userId: Long,
        @PathVariable commentId: Long,
        @RequestBody commentRequest: CommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.updateComment(userId, commentId, commentRequest))
    } // 수정

    @DeleteMapping("/comments/{commentId}")
    fun deleteComment(
        @PathVariable userId: Long,
        @PathVariable commentId: Long
    ): ResponseEntity<Unit> {
        commentService.deleteComment(userId, commentId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    } // 삭제
}