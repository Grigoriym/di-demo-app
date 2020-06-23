package ru.ftc.todoapp.login.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.android.getKoin
import org.koin.core.context.loadKoinModules
import org.koin.core.parameter.parametersOf
import ru.ftc.todoapp.core.navigation.Navigator
import ru.ftc.todoapp.core.navigation.Router
import ru.ftc.todoapp.login.R
import ru.ftc.todoapp.login.di.loginModule
import ru.ftc.todoapp.login.di.loginQualifier

class LoginActivity : AppCompatActivity() {

    internal val featureScope by lazy {
        getKoin().getOrCreateScope(toString(), loginQualifier)
    }

    private val router: Router by lazy {
        featureScope.get<Router>()
    }

    private val loginNavigator: Navigator
        get() {
            return featureScope.get<LoginNavigator> {
                parametersOf(this@LoginActivity)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_frame)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.content_frame, LoginFragment.newInstance())
                .commit()
        }
    }

    override fun onResume() {
        super.onResume()
        router.setNavigator(navigator = loginNavigator)
    }

    override fun onPause() {
        super.onPause()
        router.setNavigator(null)
    }

    companion object {

        init {
            loadKoinModules(loginModule)
        }
    }
}
