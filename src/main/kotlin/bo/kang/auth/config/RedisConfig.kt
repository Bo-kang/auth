package bo.kang.auth.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig {
    companion object{
        private val objectMapper = ObjectMapper().registerKotlinModule()

    }

    @Bean
    @Primary
    fun reactiveRedisTemplate(factory : ReactiveRedisConnectionFactory): ReactiveRedisTemplate<String, Any> {
        val serializer = StringRedisSerializer()
        val jackson2JsonRedisSerializer = Jackson2JsonRedisSerializer(objectMapper, Any::class.java)

        val serializationContext: RedisSerializationContext<String, Any> = RedisSerializationContext
            .newSerializationContext<String, Any>()
            .key(serializer)
            .hashKey(serializer)
            .value(jackson2JsonRedisSerializer)
            .hashValue(jackson2JsonRedisSerializer)
            .build()
        return ReactiveRedisTemplate(factory, serializationContext)
    }

}
