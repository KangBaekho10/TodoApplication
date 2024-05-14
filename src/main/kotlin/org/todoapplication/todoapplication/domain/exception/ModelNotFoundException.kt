package org.todoapplication.todoapplication.domain.exception

data class ModelNotFoundException(
    val modelName: String, val userId: Long):
    RuntimeException("Model $modelName not found with id $userId")
