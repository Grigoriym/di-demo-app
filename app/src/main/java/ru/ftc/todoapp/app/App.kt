package ru.ftc.todoapp.app

import android.app.Application
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import ru.ftc.todoapp.core.data.StorageModule
import ru.ftc.todoapp.core.data.StorageScope
import ru.ftc.todoapp.core.gson.GsonModule
import ru.ftc.todoapp.core.gson.GsonScope
import ru.ftc.todoapp.login.api.TaskListOpener
import ru.ftc.todoapp.login.ui.LoginActivityDependency
import ru.ftc.todoapp.presentation.LoginOpenerImpl
import ru.ftc.todoapp.presentation.TaskListOpenerImpl
import ru.ftc.todoapp.task.api.LoginOpener
import ru.ftc.todoapp.task.di.TaskDependency

@Module
internal abstract class OpenersModule {
    @Binds
    abstract fun bind1(b: TaskListOpenerImpl): TaskListOpener

    @Binds
    abstract fun bind2(b: LoginOpenerImpl): LoginOpener
}

@StorageScope
@GsonScope
@Component(modules = [StorageModule::class, OpenersModule::class, GsonModule::class])
interface AppComponent : LoginActivityDependency, TaskDependency {
    @Component.Factory
    interface F {
        fun create(@BindsInstance application: Application): AppComponent
    }
}

class App : Application(), LoginActivityDependency.DepProvider, TaskDependency.DepProvider {

    val component by lazy {
        DaggerAppComponent
            .factory()
            .create(this)
    }

    override fun getLoginFragmentDependency(): LoginActivityDependency = component
    override fun taskDependency(): TaskDependency = component
}