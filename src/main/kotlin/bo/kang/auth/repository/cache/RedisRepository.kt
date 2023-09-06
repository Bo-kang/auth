package bo.kang.auth.repository.cache

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.redis.core.ReactiveRedisOperations
import org.springframework.data.redis.core.getAndAwait
import org.springframework.data.redis.core.setAndAwait
import org.springframework.stereotype.Repository
import java.time.Duration

@Repository
class RedisRepository(
    private val reactiveRedisOperations: ReactiveRedisOperations<String, Any>,
    private val objectMapper: ObjectMapper
) {
    suspend fun set(key: String, value: String, expires: Duration = Duration.ofSeconds(300L)) = try {
        reactiveRedisOperations.opsForValue().setAndAwait(key = key, value = value, expires)
    } catch (e: Exception) {
        throw e
    }

    suspend fun set(key: String, value: Any, expires: Duration = Duration.ofSeconds(300L)) = try {
        set(key = key, value = objectMapper.writeValueAsString(value), expires)
    } catch (e: Exception) {
        throw e
    }

    suspend fun get(key: String): String? = try {
        reactiveRedisOperations.opsForValue().getAndAwait(key).toString()
    } catch (e: Exception) {
        throw e
    }

    suspend fun <T> get(key: String, clazz: Class<T>): T? =
        get(key)?.let {
            objectMapper.readValue(it, clazz)
        }


}
