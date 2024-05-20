package org.todoapplication.todoapplication.domain.todocard.model

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import org.todoapplication.todoapplication.domain.comment.model.Comment
import org.todoapplication.todoapplication.domain.comment.model.toResponse
import org.todoapplication.todoapplication.domain.todocard.dto.TodoCardResponse
import java.time.LocalDateTime

@Entity
@Table(name = "card")
class TodoCard (
    @Column
    @NotNull
    var writer : String,
    var title : String,
    var content : String,
    var date : LocalDateTime,

    @Enumerated(EnumType.STRING)
    @Column
    @NotNull
    var completed: TodoCardCompleted = TodoCardCompleted.FALSE,

    @OneToMany(mappedBy = "todoCard", fetch = FetchType.LAZY)
    val comment: MutableList<Comment> = mutableListOf()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userid : Long? = null
}

fun TodoCard.toResponse(): TodoCardResponse {
    return TodoCardResponse(
        userId = userid!!,
        title = title,
        writer = writer,
        content = content,
        date = date,
        completed = completed,
        comments = comment.map { it.toResponse() }
    )
}