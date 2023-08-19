package bo.kang.auth.dto

class Requests {
    data class LoginRequest(
        val username : String,
        val password : String,
    )

}