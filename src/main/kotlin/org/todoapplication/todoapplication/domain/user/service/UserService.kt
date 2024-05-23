package org.todoapplication.todoapplication.domain.user.service

import org.todoapplication.todoapplication.domain.user.dto.UserRequest
import org.todoapplication.todoapplication.domain.user.dto.UserResponse

interface UserService {

    fun createUser(request: UserRequest): UserResponse

    fun login(request: UserRequest): UserResponse

}