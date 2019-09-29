package ru.ftc.todoapp.feature.login.di

import dagger.Binds
import dagger.Module
import ru.ftc.todoapp.feature.login.data.LoginRepositoryImpl
import ru.ftc.todoapp.feature.login.domain.*
import ru.ftc.todoapp.feature.login.presentation.LoginPresenter
import ru.ftc.todoapp.feature.login.presentation.LoginPresenterImpl

@Module
interface LoginFragmentModule {

    @Binds
    fun bindIsLoggedInUseCase(impl: IsLoggedInUseCaseImpl): IsLoggedInUseCase

    @Binds
    fun bindLoginUseCase(impl: LoginUseCaseImpl): LoginUseCase

    @Binds
    fun bindLoginPresenter(impl: LoginPresenterImpl): LoginPresenter
}