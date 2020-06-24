package ru.ftc.todoapp.login.repo.domain

import ru.ftc.todoapp.login.repo.data.LoginRepository
import javax.inject.Inject

class IsLoggedInUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {

    operator fun invoke(): Boolean =
        loginRepository.isLoggedIn()
}