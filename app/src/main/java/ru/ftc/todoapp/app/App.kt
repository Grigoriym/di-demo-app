package ru.ftc.todoapp.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.ftc.todoapp.di.appModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            loadKoinModules(appModule)
            androidLogger(level = Level.DEBUG)
        }
    }
}
