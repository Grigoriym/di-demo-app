package ru.ftc.todoapp.login.domain

interface IsLoggedInUseCase {

    operator fun invoke(): Boolean
}

class IsLoggedInUseCaseImpl(
    private val loginRepository: LoginRepository
) : IsLoggedInUseCase {

    override fun invoke(): Boolean =
        loginRepository.isLoggedIn()
}