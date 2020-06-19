package ru.ftc.todoapp.login.presentation

import ru.ftc.todoapp.login.domain.IsLoggedInUseCase
import ru.ftc.todoapp.login.domain.LoginUseCase
import ru.ftc.todoapp.core.mvp.Presenter
import ru.ftc.todoapp.core.navigation.Destination
import ru.ftc.todoapp.core.navigation.Router

abstract class LoginPresenter: Presenter<LoginView>() {

    abstract fun onLoginClick(name: String, password: String)
}

class LoginPresenterImpl(
    private val loginUseCase: LoginUseCase,
    private val isLoggedInUseCase: IsLoggedInUseCase,
    private val router: LoginRouter
): LoginPresenter() {

    override fun onViewAttach() {
        if (isLoggedInUseCase()) {
            router.openTaskList()
        }
    }

    override fun onLoginClick(name: String, password: String) {
        if (loginUseCase(name, password)) {
            router.openTaskList()
        } else {
            view?.showError()
        }
    }
}