package ru.ftc.todoapp.feature.login.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.ftc.todoapp.app.di.scope.FragmentScope
import ru.ftc.todoapp.feature.login.ui.LoginFragment

@Module
interface LoginModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [LoginFragmentModule::class])
    fun login(): LoginFragment
}