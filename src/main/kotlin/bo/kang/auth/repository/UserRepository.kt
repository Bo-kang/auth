package bo.kang.auth.repository

import bo.kang.auth.entity.PlatformUserEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface UserRepository : CoroutineCrudRepository<PlatformUserEntity, Long> {
    suspend fun findByEmail(email : String) : PlatformUserEntity?

}
