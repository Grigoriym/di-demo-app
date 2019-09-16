package ru.ftc.todoapp.app

import android.app.Application
import ru.ftc.todoapp.app.sl.*
import ru.ftc.todoapp.data.Storage
import ru.ftc.todoapp.data.StorageImpl
import ru.ftc.todoapp.feature.login.data.LoginRepositoryImpl
import ru.ftc.todoapp.feature.login.domain.*
import ru.ftc.todoapp.feature.task.data.TaskRepositoryImpl
import ru.ftc.todoapp.feature.task.domain.*
import ru.ftc.todoapp.navigation.Router
import ru.ftc.todoapp.navigation.RouterImpl

class App : Application() {

    companion object {

        lateinit var serviceLocator: ServiceLocator
    }

    override fun onCreate() {
        super.onCreate()

        serviceLocator = ServiceLocatorImpl(
            Storage::class to InstanceProvider(StorageImpl(this@App)),

            TaskRepository::class to Factory { TaskRepositoryImpl(get()) },
            LoginRepository::class to Factory { LoginRepositoryImpl(get()) },

            IsLoggedInUseCase::class to Factory { IsLoggedInUseCaseImpl(get()) },
            LoginUseCase::class to Factory { LoginUseCaseImpl(get()) },
            LogoutUseCase::class to Factory { LogoutUseCaseImpl(get()) },

            GetTasksUseCase::class to Factory { GetTasksUseCaseImpl(get()) },
            CreateTaskUseCase::class to Factory { CreateTaskUseCaseImpl(get()) },
            DeleteTaskUseCase::class to Factory { DeleteTaskUseCaseImpl(get()) },
            UpdateTaskUseCase::class to Factory { UpdateTaskUseCaseImpl(get()) },

            Router::class to InstanceProvider(RouterImpl())
        )
    }
}