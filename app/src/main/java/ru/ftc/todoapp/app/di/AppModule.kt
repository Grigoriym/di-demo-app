package ru.ftc.todoapp.app.di

import android.content.Context
import dagger.Binds
import dagger.Module
import ru.ftc.todoapp.app.App
import ru.ftc.todoapp.app.di.scope.AppScope
import ru.ftc.todoapp.data.Storage
import ru.ftc.todoapp.data.StorageImpl
import ru.ftc.todoapp.feature.login.data.LoginRepositoryImpl
import ru.ftc.todoapp.feature.login.domain.LoginRepository
import ru.ftc.todoapp.navigation.Router
import ru.ftc.todoapp.navigation.RouterImpl

@Module
interface AppModule {

    @Binds
    fun bindContext(app: App): Context

    @AppScope
    @Binds
    fun bindStorage(impl: StorageImpl): Storage

    @AppScope
    @Binds
    fun bindRouter(impl: RouterImpl): Router

    @Binds
    fun bindLoginRepository(impl: LoginRepositoryImpl): LoginRepository
}