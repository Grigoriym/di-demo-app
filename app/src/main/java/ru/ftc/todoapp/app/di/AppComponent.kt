package ru.ftc.todoapp.app.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import ru.ftc.todoapp.app.App
import ru.ftc.todoapp.app.di.scope.AppScope

@AppScope
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        FeaturesModule::class]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<App>
}

