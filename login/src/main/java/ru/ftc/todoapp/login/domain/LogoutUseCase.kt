package ru.ftc.todoapp.login.domain

interface LogoutUseCase {

    operator fun invoke()
}

class LogoutUseCaseImpl(
    private val loginRepository: LoginRepository
): LogoutUseCase {

    override fun invoke() {
        loginRepository.logout()
    }
}