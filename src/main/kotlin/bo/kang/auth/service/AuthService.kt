package bo.kang.auth.service

import bo.kang.auth.data.Exceptions
import bo.kang.auth.dto.Requests
import bo.kang.auth.dto.Responses
import bo.kang.auth.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    suspend fun login(email: String, password: String? = null) : Responses.User {

        return runCatching {
            userRepository.findByEmail(email)?.also {
                if (!verifyPassword(
                        password,
                        it.password
                    )
                ) throw Exceptions.WrongPasswordException(" email : [$email] ")
            } ?: throw Exceptions.NotFoundUserException(" email : [$email] ")
        }.onFailure {
            throw when (it) {
                is Exceptions.NotFoundUserException, is Exceptions.WrongPasswordException -> Exceptions.BadRequestException("Please check your email or password")
                else -> it
            }
        }.getOrThrow().run {
            Responses.User.from(this)
        }
    }
    suspend fun login(req: Requests.Login): Responses.User {
        return login(req.email, req.password)
    }

    suspend fun register(req : Requests.Register){

    }


    private suspend fun verifyPassword(requestPassword: String?, password: String): Boolean =
        requestPassword?.let { passwordEncoder.matches(requestPassword, password) } ?: true
}
