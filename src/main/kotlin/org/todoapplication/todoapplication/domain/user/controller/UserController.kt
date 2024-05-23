package org.todoapplication.todoapplication.domain.user.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.todoapplication.todoapplication.domain.user.dto.UserRequest
import org.todoapplication.todoapplication.domain.user.dto.UserResponse
import org.todoapplication.todoapplication.domain.user.service.UserService

@RestController
class UserController(
    private val userService: UserService
) {
    @PostMapping("/signup")
    fun createUser(@RequestBody userRequest: UserRequest): ResponseEntity<UserResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequest))
    }

    @PostMapping("/login")
    fun login(@RequestBody userRequest: UserRequest): ResponseEntity<UserResponse>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .build()
    }
}