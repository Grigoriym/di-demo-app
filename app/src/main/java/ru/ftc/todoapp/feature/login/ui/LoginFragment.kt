package ru.ftc.todoapp.feature.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.toolbar_dark.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.direct
import org.kodein.di.generic.instance
import ru.ftc.todoapp.R
import ru.ftc.todoapp.feature.login.di.loginFragmentKodeinModule
import ru.ftc.todoapp.feature.login.presentation.LoginPresenter
import ru.ftc.todoapp.feature.login.presentation.LoginView

class LoginFragment : Fragment(), LoginView, KodeinAware {

    companion object {
        fun newInstance(): Fragment =
            LoginFragment()
    }

    override lateinit var kodein: Kodein
    private fun initKodein() {
        kodein = Kodein {
            extend((activity as KodeinAware).kodein)
            import(loginFragmentKodeinModule)
        }
    }

    private lateinit var presenter: LoginPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initKodein()
        toolbar.title = getString(R.string.login)
        presenter = direct.instance()  //if use KodeinAware interface, then 'kodein' word optional - same as kodein.direct.instance()

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