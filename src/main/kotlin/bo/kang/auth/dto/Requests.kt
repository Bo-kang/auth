package bo.kang.auth.dto

class Requests {
    data class LoginRequest(
        val email : String,
        val password : String,
    )

}