package ru.ftc.todoapp.login.di

import androidx.appcompat.app.AppCompatActivity
import ru.ftc.todoapp.core.navigation.Navigator
import ru.ftc.todoapp.login.api.TaskListOpener
import ru.ftc.todoapp.login.repo.domain.IsLoggedInUseCase
import ru.ftc.todoapp.login.repo.domain.LoginUseCase

interface LoginDependency {

    val loginUseCase: LoginUseCase

    val isLoggedInUseCase: IsLoggedInUseCase

    val taskListOpener: TaskListOpener

    fun createLoginNavigator(activity: AppCompatActivity): Navigator
}