package bo.kang.auth.data

import org.springframework.http.HttpStatus

object Exceptions {

    abstract class CustomException (
        val status : HttpStatus,
        val code : String,
        override val message : String,
        val exception : Exception? = null

    ): RuntimeException(message)

    /* 400 */
    open class BadRequestException(override val message : String, ex: Exception? = null) :
        CustomException(HttpStatus.BAD_REQUEST, "BadRequest", message, ex)

    /* 404 */
    open class NotFoundException(override val message : String, ex: Exception? = null) :
            CustomException(HttpStatus.NOT_FOUND, "NotFound", message, ex)

    class NotFoundUserException(additionalMessage : String? = null) :
            NotFoundException("User not exists - $additionalMessage")

    class WrongPasswordException(additionalMessage : String? = null) :
        NotFoundException("Wrong password - $additionalMessage")


}
