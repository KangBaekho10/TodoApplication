package org.todoapplication.todoapplication.domain.user.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.todoapplication.todoapplication.domain.user.dto.UserRequest
import org.todoapplication.todoapplication.domain.user.dto.UserResponse
import org.todoapplication.todoapplication.domain.user.model.User
import org.todoapplication.todoapplication.domain.user.model.toResponse
import org.todoapplication.todoapplication.domain.user.repository.UserRepository


@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
): UserService {

    @Transactional
    override fun createUser(request: UserRequest): UserResponse {
        return userRepository.save(
            User(
                email = request.email,
                password = request.password
            )
        ).toResponse()
    }

    @Transactional
    override fun login(request: UserRequest): UserResponse {
        val user = userRepository.findByEmailAndPassword(request.email, request.password)
            if (user.email == request.email && user.password == request.password) {
                return user.toResponse()
            } else {
                throw IllegalArgumentException("Writer or Password does not match.")
            }
    }
}