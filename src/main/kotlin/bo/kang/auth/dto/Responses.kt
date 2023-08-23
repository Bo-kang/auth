package bo.kang.auth.dto

import bo.kang.auth.data.UserStatus


class Responses {
    data class User(
        val id : Long,
        val username: String,
        val email: String,
        val status: UserStatus
    ) {
        companion object {
            fun from(user: bo.kang.auth.entity.User): User {
                return User(
                    id = user.id,
                    username = user.username,
                    email = user.email,
                    status = user.status
                )
            }
        }
    }
}