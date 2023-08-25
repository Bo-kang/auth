package bo.kang.auth.controller

import bo.kang.auth.data.AuthConstants.BO_USER_ID
import bo.kang.auth.dto.Requests
import bo.kang.auth.service.AuthService
import bo.kang.auth.util.UserTokenProvider
import bo.kang.auth.util.Utils
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ServerWebExchange

@RestController
class AuthController(
    private val authService: AuthService,
    private val userTokenProvider: UserTokenProvider
) {
    @PostMapping("login")
    suspend fun login(
        exchange: ServerWebExchange,
        @RequestBody req: Requests.Login
    ) {
        authService.login(req)
            .also {
                exchange.response.headers.set(
                    HttpHeaders.SET_COOKIE,
                    Utils.CookieUtils.generateCookie(BO_USER_ID, userTokenProvider.generateUserToken(it)).toString()

                )
            }
    }

    @PostMapping("register")
    suspend fun register(
        exchange: ServerWebExchange,
        @RequestBody req: Requests.Login
    ){
        authService.login(req.email, req.password)
            .also {
                exchange.response.headers.set(
                    HttpHeaders.SET_COOKIE,
                    Utils.CookieUtils.generateCookie(BO_USER_ID, userTokenProvider.generateUserToken(it)).toString()

                )
            }
    }

    @PostMapping("logout")
    suspend fun logout() {
    }
}