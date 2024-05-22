package org.todoapplication.todoapplication.domain.todocard.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

data class CreateTodoCardRequest(
    @field:NotBlank
    val writer : String,

    @field:NotBlank
    @field:Size(min = 1, max = 200)
    val title: String,

    @field:NotBlank
    @field:Size(min = 1, max = 1000)
    val content : String,

    val date : LocalDateTime
)