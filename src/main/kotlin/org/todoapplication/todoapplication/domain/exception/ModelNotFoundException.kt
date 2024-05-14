package org.todoapplication.todoapplication.domain.exception

data class ModelNotFoundException(
    val modelName: String, val userid: Long):
    RuntimeException("Model $modelName not found with id $userid")
