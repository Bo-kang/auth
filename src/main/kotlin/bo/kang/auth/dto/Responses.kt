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
            fun from(platformUserEntity: bo.kang.auth.entity.PlatformUserEntity): User {
                return User(
                    id = platformUserEntity.id,
                    username = platformUserEntity.username,
                    email = platformUserEntity.email,
                    status = platformUserEntity.status
                )
            }
        }
    }
}