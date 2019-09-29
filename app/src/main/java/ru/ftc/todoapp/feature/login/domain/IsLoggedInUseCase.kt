package ru.ftc.todoapp.feature.login.domain

import javax.inject.Inject

interface IsLoggedInUseCase {

    operator fun invoke(): Boolean
}

class IsLoggedInUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository
) : IsLoggedInUseCase {

    override fun invoke(): Boolean =
        loginRepository.isLoggedIn()
}