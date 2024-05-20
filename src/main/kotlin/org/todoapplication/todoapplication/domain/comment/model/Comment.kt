package org.todoapplication.todoapplication.domain.comment.model

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import org.todoapplication.todoapplication.domain.comment.dto.CommentResponse
import org.todoapplication.todoapplication.domain.todocard.model.TodoCard

@Entity
@Table(name="comment")
class Comment (
    @Column
    @NotNull
    var content: String,
    var writer: String,
    var password: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    val todoCard: TodoCard,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var commentid : Long? = null
}

fun Comment.toResponse(): CommentResponse{
    return CommentResponse(
        commentId = commentid!!,
        content = content,
        writer = writer,
    )
}