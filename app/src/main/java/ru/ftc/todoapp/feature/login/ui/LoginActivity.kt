package ru.ftc.todoapp.feature.login.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import ru.ftc.todoapp.R
import ru.ftc.todoapp.app.BaseActivity
import ru.ftc.todoapp.navigation.Router
import javax.inject.Inject

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_frame)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.content_frame, LoginFragment.newInstance())
                .commit()
        }
    }

    @Inject
    lateinit var router: Router

    override fun onResumeFragments() {
        super.onResumeFragments()
        router.setNavigator(LoginNavigator(this))
    }

    override fun onPause() {
        super.onPause()
        router.setNavigator(null)
    }
}