package org.todoapplication.todoapplication.domain.todocard.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.todoapplication.todoapplication.domain.todocard.model.TodoCard

interface TodoCardRepository: JpaRepository<TodoCard, Long> {
    fun findAllBy(pageable: Pageable): Page<TodoCard>
}