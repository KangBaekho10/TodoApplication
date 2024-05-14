package org.todoapplication.todoapplication.domain.todocard.service

import org.todoapplication.todoapplication.domain.todocard.dto.CreateTodoCardRequest
import org.todoapplication.todoapplication.domain.todocard.dto.TodoCardResponse
import org.todoapplication.todoapplication.domain.todocard.dto.UpdateTodoCardRequest

interface TodoCardService {
    fun getAllTodoCardList(): List<TodoCardResponse>

    fun getTodoCardById(userid: Long): TodoCardResponse

    fun createTodoCard(request: CreateTodoCardRequest): TodoCardResponse

    fun updateTodoCard(userid: Long, request: UpdateTodoCardRequest): TodoCardResponse

    fun deleteTodoCard(userid: Long)
}