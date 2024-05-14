package org.todoapplication.todoapplication.domain.todocard.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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

    @GetMapping("/{userid}")
    fun getTodoCard(@PathVariable userid: Long) : ResponseEntity<TodoCardResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoCardService.getTodoCardById(userid))

    } // 단건 조회

    @GetMapping
    fun getTodoCardList(): ResponseEntity<List<TodoCardResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoCardService.getAllTodoCardList())
    } // 목록 조회

    @PostMapping
    fun createTodoCard(@RequestBody createTodoCardRequest: CreateTodoCardRequest): ResponseEntity<TodoCardResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(todoCardService.createTodoCard(createTodoCardRequest))
    } // 생성

    @PutMapping("/{userid}")
    fun updateTodoCard(
        @PathVariable userid: Long,
        @RequestBody updateTodoCardRequest: UpdateTodoCardRequest
    ) : ResponseEntity<TodoCardResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoCardService.updateTodoCard(userid, updateTodoCardRequest))
    } // 수정

    @DeleteMapping("/{userid}")
    fun deleteTodoCard(@PathVariable userid: Long) : ResponseEntity<Unit> {
        todoCardService.deleteTodoCard(userid)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    } // 삭제
}