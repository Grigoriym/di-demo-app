package ru.ftc.todoapp.feature.login.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.ftc.todoapp.R
import ru.ftc.todoapp.app.App
import ru.ftc.todoapp.app.sl.get
import ru.ftc.todoapp.app.sl.serviceLocator
import ru.ftc.todoapp.navigation.Router

class LoginActivity : AppCompatActivity() {

    private val router: Router by serviceLocator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_frame)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.content_frame, LoginFragment.newInstance())
                .commit()
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        router.setNavigator(LoginNavigator(this))
    }

    override fun onPause() {
        super.onPause()
        router.setNavigator(null)
    }
}