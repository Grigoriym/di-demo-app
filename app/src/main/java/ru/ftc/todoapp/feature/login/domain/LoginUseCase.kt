package ru.ftc.todoapp.feature.login.domain

import javax.inject.Inject

interface LoginUseCase {

    operator fun invoke(name: String, password: String): Boolean
}

class LoginUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository
): LoginUseCase {

    override fun invoke(name: String, password: String): Boolean =
        loginRepository.login(name, password)
}