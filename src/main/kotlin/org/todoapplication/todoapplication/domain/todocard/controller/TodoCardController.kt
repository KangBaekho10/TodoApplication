package org.todoapplication.todoapplication.domain.todocard.controller

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import org.todoapplication.todoapplication.domain.todocard.dto.CreateTodoCardRequest
import org.todoapplication.todoapplication.domain.todocard.dto.TodoCardResponse
import org.todoapplication.todoapplication.domain.todocard.dto.UpdateTodoCardRequest
import org.todoapplication.todoapplication.domain.todocard.service.TodoCardService

@RequestMapping("/todocards")
@RestController
class TodoCardController(
    private val todoCardService: TodoCardService
) {

    @GetMapping("/{userId}")
    fun getTodoCard(@PathVariable userId: Long): ResponseEntity<TodoCardResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoCardService.getTodoCardById(userId))
    } // 단건 조회

    @GetMapping
    fun getTodoCardList(
        @RequestParam(defaultValue = "desc") sort: String,
        @RequestParam(defaultValue = "") writer: String
    ): ResponseEntity<List<TodoCardResponse>> {
        val todoCards = if (writer.isNotEmpty()) todoCardService.getAllTodoCardList()
            .filter { it.writer == writer } else todoCardService.getAllTodoCardList()

        val sortedTodoCard = when (sort) {
            "desc" -> todoCards.sortedByDescending { it.date }
            "asc" -> todoCards.sortedBy { it.date }
            else -> throw IllegalArgumentException("Unsupported sorting: $sort")
        }
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(sortedTodoCard)
    } // 목록 조회

    @PostMapping
    fun createTodoCard(
        @Valid @RequestBody createTodoCardRequest: CreateTodoCardRequest,
        bindingResult: BindingResult
    ): ResponseEntity<TodoCardResponse> {
        return if (bindingResult.hasErrors()) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        } else {
            ResponseEntity
                .status(HttpStatus.CREATED)
                .body(todoCardService.createTodoCard(createTodoCardRequest))
        }
    } // 생성

    @PutMapping("/{userId}")
    fun updateTodoCard(
        @PathVariable userId: Long,
        @Valid @RequestBody updateTodoCardRequest: UpdateTodoCardRequest,
        bindingResult: BindingResult
    ): ResponseEntity<TodoCardResponse> {
        return if(bindingResult.hasErrors()) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        } else {
            ResponseEntity
                .status(HttpStatus.OK)
                .body(todoCardService.updateTodoCard(userId, updateTodoCardRequest))
        }
    } // 수정

    @DeleteMapping("/{userId}")
    fun deleteTodoCard(@PathVariable userId: Long): ResponseEntity<Unit> {
        todoCardService.deleteTodoCard(userId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    } // 삭제
}