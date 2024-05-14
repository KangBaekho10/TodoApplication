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


    override fun getTodoCardById(userid: Long): TodoCardResponse{
        val todoCard = todoCardRepository.findByIdOrNull(userid) ?: throw ModelNotFoundException("TodoCard", userid)
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
    override fun updateTodoCard(userid: Long, request: UpdateTodoCardRequest): TodoCardResponse {
        val todoCard = todoCardRepository.findByIdOrNull(userid) ?: throw ModelNotFoundException("TodoCard", userid)
        val (writer, title, content) = request

        todoCard.writer = writer
        todoCard.title = title
        todoCard.content = content

        return todoCardRepository.save(todoCard).toResponse()
    }

    @Transactional
    override fun deleteTodoCard(userid: Long){
        val todoCard = todoCardRepository.findByIdOrNull(userid) ?: throw ModelNotFoundException("TodoCard", userid)
        todoCardRepository.delete(todoCard)
    }
}