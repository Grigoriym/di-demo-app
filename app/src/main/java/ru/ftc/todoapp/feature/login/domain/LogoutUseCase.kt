package ru.ftc.todoapp.feature.login.domain

import javax.inject.Inject

interface LogoutUseCase {

    operator fun invoke()
}

class LogoutUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository
): LogoutUseCase {

    override fun invoke() {
        loginRepository.logout()
    }
}