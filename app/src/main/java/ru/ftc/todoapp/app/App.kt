package ru.ftc.todoapp.app

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider
import ru.ftc.todoapp.data.Storage
import ru.ftc.todoapp.data.StorageImpl
import ru.ftc.todoapp.di.todoAppKodeinModule
import ru.ftc.todoapp.feature.login.data.LoginRepositoryImpl
import ru.ftc.todoapp.feature.login.domain.*
import ru.ftc.todoapp.feature.task.data.TaskRepositoryImpl
import ru.ftc.todoapp.feature.task.domain.*
import ru.ftc.todoapp.navigation.Router
import ru.ftc.todoapp.navigation.RouterImpl

class App : Application(), KodeinAware {
    override val kodein = Kodein{
        import(todoAppKodeinModule)
        bind<App>() with provider { this@App }
    }
}