package ru.ftc.todoapp.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_login.*
import ru.ftc.todoapp.core.synthetic.exported.exported_toolbar
import ru.ftc.todoapp.login.R
import ru.ftc.todoapp.login.di.LoginDependency
import ru.ftc.todoapp.login.presentation.LoginPresenter
import ru.ftc.todoapp.login.presentation.LoginPresenterImpl
import ru.ftc.todoapp.login.presentation.LoginView

class LoginFragment : Fragment(), LoginView {

    companion object {

        fun newInstance(): Fragment =
            LoginFragment()
    }

    // FIXME Dependencies from App
    private val dependency: LoginDependency
        get() = activity?.application as LoginDependency

    private lateinit var presenter: LoginPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        exported_toolbar.title = getString(R.string.login)

        // FIXME Providing dependencies from App
        presenter = LoginPresenterImpl(
            loginUseCase = dependency.loginUseCase,
            isLoggedInUseCase = dependency.isLoggedInUseCase,
            router = dependency.loginRouter
        )
        presenter.attachView(this)

        login_enter.setOnClickListener {
            presenter.onLoginClick(
                login_name.text.toString(),
                login_password.text.toString()
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    override fun showError() {
        login_password.error = getString(R.string.login_error)
    }
}