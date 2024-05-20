package org.todoapplication.todoapplication.domain.todocard.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.todoapplication.todoapplication.domain.exception.ModelNotFoundException
import org.todoapplication.todoapplication.domain.todocard.dto.CreateTodoCardRequest
import org.todoapplication.todoapplication.domain.todocard.dto.TodoCardResponse
import org.todoapplication.todoapplication.domain.todocard.dto.UpdateTodoCardRequest
import org.todoapplication.todoapplication.domain.todocard.model.TodoCard
import org.todoapplication.todoapplication.domain.todocard.repository.TodoCardRepository
import org.todoapplication.todoapplication.domain.todocard.model.toResponse

@Service
class TodoCardServiceImpl(
    private val todoCardRepository: TodoCardRepository,
): TodoCardService {

    override fun getAllTodoCardList(): List<TodoCardResponse>{
        return todoCardRepository.findAll().map { it.toResponse() }
    }

    override fun getTodoCardById(userId: Long): TodoCardResponse{
        val todoCard = todoCardRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("TodoCard", userId)
        return todoCard.toResponse()
    }

    @Transactional
    override fun createTodoCard(request: CreateTodoCardRequest): TodoCardResponse{
        return todoCardRepository.save(
            TodoCard(
                writer = request.writer,
                title = request.title,
                content = request.content,
                date = request.date,
            )
        ).toResponse()
    }

    @Transactional
    override fun updateTodoCard(userId: Long, request: UpdateTodoCardRequest): TodoCardResponse {
        val todoCard = todoCardRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("TodoCard", userId)
        val (writer, title, content) = request

        todoCard.writer = writer
        todoCard.title = title
        todoCard.content = content

        return todoCardRepository.save(todoCard).toResponse()
    }

    @Transactional
    override fun deleteTodoCard(userId: Long){
        val todoCard = todoCardRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("TodoCard", userId)
        todoCardRepository.delete(todoCard)
    }
}