package org.todoapplication.todoapplication.domain.user.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.todoapplication.todoapplication.domain.user.model.User

interface UserRepository: JpaRepository<User, Long> {
    fun findByEmailAndPassword(email: String, password: String): User
}