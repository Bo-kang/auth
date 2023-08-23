package bo.kang.auth.entity

import bo.kang.auth.data.UserStatus
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

@Table("user")
data class User(
    @Id
    val id : Long,
    val username : String,
    val email : String,
    val password: String,
    val status : UserStatus,
    val createdAt: Instant? = Instant.now(),
    val updatedAt: Instant? = Instant.now()
)
