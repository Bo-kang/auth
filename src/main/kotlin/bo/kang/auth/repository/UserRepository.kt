package bo.kang.auth.repository

import bo.kang.auth.entity.User
import org.springframework.data.repository.kotlin.CoroutineCrudRepository


interface UserRepository : CoroutineCrudRepository<Long, User> {
}