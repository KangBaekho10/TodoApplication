package org.todoapplication.todoapplication.domain.todocard.model

import jakarta.persistence.*
import org.todoapplication.todoapplication.domain.todocard.dto.TodoCardResponse
import java.time.LocalDateTime

@Entity
@Table(name = "card")
class TodoCard (
    @Column(name = "writer", nullable = false)
    var writer : String,

    @Column(name = "title", nullable = false)
    var title : String,

    @Column(name = "content", nullable = false)
    var content : String,

    @Column(name = "date", nullable = false)
    var date : LocalDateTime,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userid : Long? = null
}

fun TodoCard.toResponse(): TodoCardResponse {
    return TodoCardResponse(
        userid = userid!!,
        title = title,
        writer = writer,
        content = content,
        date = date,
    )
}