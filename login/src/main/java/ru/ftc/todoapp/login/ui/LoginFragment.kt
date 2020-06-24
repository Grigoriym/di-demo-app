package ru.ftc.todoapp.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import dagger.Component
import kotlinx.android.synthetic.main.fragment_login.*
import ru.ftc.todoapp.core.data.Storage
import ru.ftc.todoapp.core.synthetic.exported.exported_toolbar
import ru.ftc.todoapp.login.R
import ru.ftc.todoapp.login.api.TaskListOpener
import ru.ftc.todoapp.login.presentation.LoginPresenter
import ru.ftc.todoapp.login.presentation.LoginView


interface LoginFragmentDependency {
    interface Provider {
        fun getLoginFragmentDependency(): LoginFragmentDependency
    }

    fun storage(): Storage
    fun activity(): FragmentActivity
    fun taskListOpener(): TaskListOpener
}

@Component(dependencies = [LoginFragmentDependency::class])
internal interface LoginFragmentComponent {
    fun presenter(): LoginPresenter
}

class LoginFragment : Fragment(), LoginView {

    companion object {
        fun newInstance(): Fragment = LoginFragment()
    }

    private val presenter by lazy(LazyThreadSafetyMode.NONE) {
        DaggerLoginFragmentComponent
            .builder()
            .loginFragmentDependency((activity as LoginFragmentDependency.Provider).getLoginFragmentDependency())
            .build()
            .presenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        exported_toolbar.title = getString(R.string.login)

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