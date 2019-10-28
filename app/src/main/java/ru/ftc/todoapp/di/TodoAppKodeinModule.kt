package ru.ftc.todoapp.di

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import ru.ftc.todoapp.app.App
import ru.ftc.todoapp.data.Storage
import ru.ftc.todoapp.data.StorageImpl
import ru.ftc.todoapp.feature.login.data.LoginRepositoryImpl
import ru.ftc.todoapp.feature.login.domain.LoginRepository
import ru.ftc.todoapp.feature.login.domain.LogoutUseCase
import ru.ftc.todoapp.feature.login.domain.LogoutUseCaseImpl
import ru.ftc.todoapp.navigation.Router
import ru.ftc.todoapp.navigation.RouterImpl

val todoAppKodeinModule = Kodein.Module("todoAppKodeinModule"){
    bind<Storage>() with singleton { StorageImpl(instance<App>()) }
    bind<Router>() with singleton { RouterImpl() }
    bind<LoginRepository>() with singleton { LoginRepositoryImpl(instance()) }
    bind<LogoutUseCase>() with singleton { LogoutUseCaseImpl(instance()) }

}