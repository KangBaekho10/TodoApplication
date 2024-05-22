package org.todoapplication.todoapplication.domain.todocard.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UpdateTodoCardRequest(
    val writer: String,

    @field:NotBlank
    @field:Size(min = 1, max = 200)
    val title: String,

    @field:NotBlank
    @field:Size(min = 1, max = 1000)
    val content : String,

    val completed : Boolean,
)