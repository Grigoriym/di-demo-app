package ru.ftc.todoapp.di

import android.app.Activity
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.ftc.todoapp.core.data.Storage
import ru.ftc.todoapp.core.data.StorageImpl
import ru.ftc.todoapp.core.di.appQualifier
import ru.ftc.todoapp.core.navigation.Navigator
import ru.ftc.todoapp.core.navigation.Router
import ru.ftc.todoapp.core.navigation.RouterImpl
import ru.ftc.todoapp.navigation.AppNavigator

val appModule = module {

    single<Storage> {
        StorageImpl(androidContext())
    }

    single<Router> {
        RouterImpl()
    }

    factory<Navigator>(appQualifier) { (activity: Activity) ->
        AppNavigator(activity)
    }
}
