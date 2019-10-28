package ru.ftc.todoapp.feature.login.di

import androidx.fragment.app.Fragment
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import ru.ftc.todoapp.feature.login.domain.*
import ru.ftc.todoapp.feature.login.presentation.LoginPresenter
import ru.ftc.todoapp.feature.login.presentation.LoginPresenterImpl
import ru.ftc.todoapp.feature.login.ui.LoginFragment
import ru.ftc.todoapp.feature.login.ui.LoginNavigator
import ru.ftc.todoapp.navigation.Navigator

val loginKodeinModule = Kodein.Module("loginKodeinModule ") {
    bind<LoginUseCase>() with singleton { LoginUseCaseImpl(instance()) }
    bind<IsLoggedInUseCase>() with singleton { IsLoggedInUseCaseImpl(instance()) }
}

val loginActivityModule = Kodein.Module("loginActivityModule") {
    bind<Navigator>() with singleton { LoginNavigator(instance()) }
    bind<Fragment>("LoginFragment") with provider { LoginFragment.newInstance() }
}

val loginFragmentKodeinModule = Kodein.Module("loginFragmentKodeinModule") {
    bind<LoginPresenter>() with singleton { LoginPresenterImpl(instance(), instance(), instance()) }
}