package org.todoapplication.todoapplication.domain.user.model

import jakarta.persistence.*
import org.todoapplication.todoapplication.domain.user.dto.UserResponse

@Entity
@Table(name = "users")
class User(
    var email: String,

    @Column(name = "password")
    var password: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var loginId: Long? = null
}

fun User.toResponse(): UserResponse {
    return UserResponse(
        loginId = loginId!!,
        email = email,
    )
}