package ru.ftc.todoapp.login.di

import androidx.appcompat.app.AppCompatActivity
import ru.ftc.todoapp.core.navigation.Navigator
import ru.ftc.todoapp.login.domain.IsLoggedInUseCase
import ru.ftc.todoapp.login.domain.LoginUseCase
import ru.ftc.todoapp.login.presentation.LoginRouter

interface LoginDependency {

    val loginUseCase: LoginUseCase

    val isLoggedInUseCase: IsLoggedInUseCase

    val loginRouter: LoginRouter

    fun createLoginNavigator(activity: AppCompatActivity): Navigator
}