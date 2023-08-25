package bo.kang.auth.util

import bo.kang.auth.dto.Responses
import bo.kang.auth.dto.VerifiedUser
import io.jsonwebtoken.Claims
import io.jsonwebtoken.security.Keys
import io.klogging.logger
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component
import javax.crypto.SecretKey
import kotlin.io.encoding.Base64

@Component
class UserTokenProvider(

) {

    companion object {
        const val keySource = "qBNGooBpSENJbDFPzYLvTbAaZoGVxscO" //@Todo Move to property
        val logger = logger("token-provider")
    }

    val tokenKey: SecretKey = Keys.hmacShaKeyFor(keySource.toByteArray())


    fun generateUserToken(user: Responses.User): String {
        return Utils.JwtUtils.generateJwt(
            sub = user.id.toString(),
            key = tokenKey,
            claims = mapOf("un" to user.username, "st" to user.status)
        )
    }

    suspend fun verifyUserToken(token: String) : VerifiedUser {
        return runCatching {
            Utils.JwtUtils.validateJwt(token, tokenKey)
        }.onFailure {
            logger.warn(it)
        }.getOrThrow()
            .run {
                VerifiedUser(
                    id = this.body[Claims.SUBJECT].toString().toLong(),
                    expiresAt = this.body[Claims.EXPIRATION].toString().toLong()
                )
            }
    }

}