package bo.kang.auth.entity

import java.time.Instant

data class User(
    val id : Long,
    val username : String,
    val email : String,
    val password: String,
    val createdAt: Instant? = Instant.now(),
    val updatedAt: Instant? = Instant.now()

)
