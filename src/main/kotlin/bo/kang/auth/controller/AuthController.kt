package bo.kang.auth.controller

import bo.kang.auth.dto.Requests
import bo.kang.auth.service.AuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    authService: AuthService
) {
    @PostMapping("login")
    suspend fun login(
        @RequestBody req : Requests.LoginRequest
    ){}

    @PostMapping("logout")
    suspend fun logout(){}
}