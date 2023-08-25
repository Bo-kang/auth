package bo.kang.auth.dto

class Requests {
    data class Login(
        val email : String,
        val password : String,
    )

    data class Register(
        val email : String,
        val userName : String,
        val password : String,

    )
}