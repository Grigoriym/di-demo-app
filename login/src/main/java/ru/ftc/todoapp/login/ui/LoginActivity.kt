package ru.ftc.todoapp.login.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import dagger.BindsInstance
import dagger.Component
import ru.ftc.todoapp.core.data.Storage
import ru.ftc.todoapp.login.R
import ru.ftc.todoapp.login.api.TaskListOpener

interface LoginActivityDependency {
    interface DepProvider {
        fun getLoginFragmentDependency(): LoginActivityDependency
    }

    fun storage(): Storage
    fun taskListOpener(): TaskListOpener
}

@Component(dependencies = [LoginActivityDependency::class])
internal interface LoginActivityComponent: LoginFragmentDependency {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance activity: FragmentActivity,
            dep: LoginActivityDependency
        ): LoginActivityComponent
    }
}

class LoginActivity : AppCompatActivity(), LoginFragmentDependency.Provider {

    private val component by lazy {
        DaggerLoginActivityComponent
            .factory()
            .create(
                this,
                (application as LoginActivityDependency.DepProvider).getLoginFragmentDependency()
            )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Thread.sleep(500)

        setContentView(R.layout.activity_frame)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.content_frame, LoginFragment.newInstance())
                .commit()
        }
    }

    override fun getLoginFragmentDependency(): LoginFragmentDependency = component

}