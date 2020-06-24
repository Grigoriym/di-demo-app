package ru.ftc.todoapp.login.repo.domain

import ru.ftc.todoapp.login.repo.data.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {

    operator fun invoke(name: String, password: String): Boolean =
        loginRepository.login(name, password)
}