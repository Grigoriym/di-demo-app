package ru.ftc.todoapp.login.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.ftc.todoapp.core.di.CoreDependency
import ru.ftc.todoapp.login.R
import ru.ftc.todoapp.login.di.LoginDependency

class LoginActivity : AppCompatActivity() {

    // FIXME Dependencies from App
    private val loginDependency: LoginDependency
        get() = application as LoginDependency

    private val coreDependency: CoreDependency
        get() = application as CoreDependency

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_frame)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.content_frame, LoginFragment.newInstance())
                .commit()
        }
    }

}