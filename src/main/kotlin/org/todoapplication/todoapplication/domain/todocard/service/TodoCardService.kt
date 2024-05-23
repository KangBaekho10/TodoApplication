package org.todoapplication.todoapplication.domain.todocard.service

import org.springframework.data.domain.Page
import org.todoapplication.todoapplication.domain.todocard.dto.CreateTodoCardRequest
import org.todoapplication.todoapplication.domain.todocard.dto.TodoCardResponse
import org.todoapplication.todoapplication.domain.todocard.dto.UpdateTodoCardRequest

interface TodoCardService {
    fun getAllTodoCardList(pageNo: Int, count: Int): Page<TodoCardResponse>

    fun getTodoCardById(userId: Long): TodoCardResponse

    fun createTodoCard(request: CreateTodoCardRequest): TodoCardResponse

    fun updateTodoCard(userId: Long, request: UpdateTodoCardRequest): TodoCardResponse

    fun deleteTodoCard(userId: Long)
}