package ru.ftc.todoapp.login.di

import android.app.Activity
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import ru.ftc.todoapp.core.di.appQualifier
import ru.ftc.todoapp.login.data.LoginRepositoryImpl
import ru.ftc.todoapp.login.domain.*
import ru.ftc.todoapp.login.presentation.LoginPresenter
import ru.ftc.todoapp.login.presentation.LoginPresenterImpl
import ru.ftc.todoapp.login.presentation.LoginRouter
import ru.ftc.todoapp.login.presentation.LoginRouterImpl
import ru.ftc.todoapp.login.ui.LoginNavigator

val loginModule = module {

    single<LoginRepository> {
        LoginRepositoryImpl(storage = get())
    }

    factory<LoginUseCase> {
        LoginUseCaseImpl(loginRepository = get())
    }

    factory<LogoutUseCase> {
        LogoutUseCaseImpl(loginRepository = get())
    }

    factory<IsLoggedInUseCase> {
        IsLoggedInUseCaseImpl(loginRepository = get())
    }

    scope(loginQualifier) {

        scoped { (activity: Activity) ->
            LoginNavigator(activity, get(appQualifier) { parametersOf(activity) })
        }

        scoped<LoginRouter> {
            LoginRouterImpl(router = get())
        }

        scoped<LoginPresenter> {
            LoginPresenterImpl(loginUseCase = get(), isLoggedInUseCase = get(), router = get())
        }
    }
}
