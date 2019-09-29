package ru.ftc.todoapp.app.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.ftc.todoapp.app.di.scope.ActivityScope
import ru.ftc.todoapp.feature.login.di.LoginModule
import ru.ftc.todoapp.feature.login.ui.LoginActivity
import ru.ftc.todoapp.feature.main.MainActivity
import ru.ftc.todoapp.feature.main.di.MainModule

@Module
interface FeaturesModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    fun main(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [LoginModule::class])
    fun login(): LoginActivity
}