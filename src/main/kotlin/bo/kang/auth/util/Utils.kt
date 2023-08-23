package bo.kang.auth.util

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import org.springframework.http.ResponseCookie
import java.security.Key
import java.util.*

object Utils {
    object JwtUtils {
        fun generateJwt(sub: String, key: Key, expiresIn: Int = 3600, claims: Map<String, Any>?): String {
            val now = Date()
            val expiredAt = Date(now.time + expiresIn * 1000)
            return Jwts.builder()
                .setSubject(sub)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiredAt)
                .signWith(key)
                .compact()
        }

        fun validateJwt(token: String, key: Key): Jws<Claims> =
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)

    }

    object CookieUtils{
        fun generateCookie(cookieName : String, token : String): ResponseCookie {
            return ResponseCookie.from(cookieName, token)
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .path("/")
                .build()
        }
    }

}