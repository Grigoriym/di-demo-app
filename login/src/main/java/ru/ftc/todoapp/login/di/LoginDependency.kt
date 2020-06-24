package ru.ftc.todoapp.login.di

import ru.ftc.todoapp.login.api.TaskListOpener
import ru.ftc.todoapp.login.repo.domain.IsLoggedInUseCase
import ru.ftc.todoapp.login.repo.domain.LoginUseCase

interface LoginDependency {

    val loginUseCase: LoginUseCase

    val isLoggedInUseCase: IsLoggedInUseCase

    val taskListOpener: TaskListOpener
}