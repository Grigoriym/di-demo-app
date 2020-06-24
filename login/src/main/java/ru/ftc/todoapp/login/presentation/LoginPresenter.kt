package ru.ftc.todoapp.login.presentation

import ru.ftc.todoapp.core.mvp.Presenter
import ru.ftc.todoapp.login.repo.domain.IsLoggedInUseCase
import ru.ftc.todoapp.login.repo.domain.LoginUseCase
import javax.inject.Inject

internal class LoginPresenter @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val isLoggedInUseCase: IsLoggedInUseCase,
    private val router: LoginRouter
) : Presenter<LoginView>() {

    override fun onViewAttach() {
        if (isLoggedInUseCase()) {
            router.openTaskList()
        }
    }

    fun onLoginClick(name: String, password: String) {
        if (loginUseCase(name, password)) {
            router.openTaskList()
        } else {
            view?.showError()
        }
    }
}