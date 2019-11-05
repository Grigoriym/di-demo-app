package ru.ftc.todoapp.feature.login.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import ru.ftc.todoapp.R
import ru.ftc.todoapp.feature.login.di.loginActivityModule
import ru.ftc.todoapp.feature.login.di.loginKodeinModule
import ru.ftc.todoapp.navigation.Router

class LoginActivity : AppCompatActivity(), KodeinAware {
    override lateinit var kodein: Kodein
    private fun initKodein() {
        kodein = Kodein {
            extend((application as KodeinAware).kodein)
            import(loginKodeinModule)
            import(loginActivityModule)
            bind<AppCompatActivity>() with provider { this@LoginActivity }
        }
    }

    private val router: Router by lazy { kodein.direct.instance<Router>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initKodein()
        setContentView(R.layout.activity_frame)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.content_frame, direct.instance("LoginFragment"))
                .commit()
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        router.setNavigator(kodein.direct.instance())
    }

    override fun onPause() {
        super.onPause()
        router.setNavigator(null)
    }
}