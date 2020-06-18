package ru.ftc.todoapp.app

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import ru.ftc.todoapp.core.data.Storage
import ru.ftc.todoapp.core.data.StorageImpl
import ru.ftc.todoapp.core.di.CoreDependency
import ru.ftc.todoapp.core.navigation.Navigator
import ru.ftc.todoapp.core.navigation.Router
import ru.ftc.todoapp.core.navigation.RouterImpl
import ru.ftc.todoapp.login.data.LoginRepositoryImpl
import ru.ftc.todoapp.login.di.LoginDependency
import ru.ftc.todoapp.login.domain.*
import ru.ftc.todoapp.login.presentation.LoginRouter
import ru.ftc.todoapp.presentation.LoginRouterImpl
import ru.ftc.todoapp.presentation.TaskRouterImpl
import ru.ftc.todoapp.task.data.TaskRepositoryImpl
import ru.ftc.todoapp.task.di.TaskDependency
import ru.ftc.todoapp.task.domain.*
import ru.ftc.todoapp.task.presentation.TaskRouter
import ru.ftc.todoapp.ui.LoginNavigator

class App : Application(), CoreDependency, LoginDependency, TaskDependency {

    private lateinit var _storage: Storage

    private lateinit var _taskRepository: TaskRepository
    private lateinit var _loginRepository: LoginRepository

    private lateinit var _isLoggedInUseCase: IsLoggedInUseCase
    private lateinit var _loginUseCase: LoginUseCase
    private lateinit var _logoutUseCase: LogoutUseCase

    private lateinit var _getTasksUseCase: GetTasksUseCase
    private lateinit var _createTaskUseCase: CreateTaskUseCase
    private lateinit var _deleteTaskUseCase: DeleteTaskUseCase
    private lateinit var _updateTaskUseCase: UpdateTaskUseCase

    private lateinit var _router: Router
    private lateinit var _loginRouter: LoginRouter
    private lateinit var _taskRouter: TaskRouter

    override fun onCreate() {
        super.onCreate()

        _storage = StorageImpl(this)

        _taskRepository = TaskRepositoryImpl(_storage)
        _loginRepository = LoginRepositoryImpl(_storage)

        _isLoggedInUseCase = IsLoggedInUseCaseImpl(_loginRepository)
        _loginUseCase = LoginUseCaseImpl(_loginRepository)
        _logoutUseCase = LogoutUseCaseImpl(_loginRepository)

        _getTasksUseCase = GetTasksUseCaseImpl(_taskRepository)
        _createTaskUseCase = CreateTaskUseCaseImpl(_taskRepository)
        _deleteTaskUseCase = DeleteTaskUseCaseImpl(_taskRepository)
        _updateTaskUseCase = UpdateTaskUseCaseImpl(_taskRepository)

        _router = RouterImpl()
        _loginRouter = LoginRouterImpl(_router)
        _taskRouter = TaskRouterImpl(_router)
    }

    override val router: Router
        get() = _router

    override val loginUseCase: LoginUseCase
        get() = _loginUseCase

    override val isLoggedInUseCase: IsLoggedInUseCase
        get() = _isLoggedInUseCase

    override val loginRouter: LoginRouter
        get() = _loginRouter

    override val getTasksUseCase: GetTasksUseCase
        get() = _getTasksUseCase

    override val createTaskUseCase: CreateTaskUseCase
        get() = _createTaskUseCase

    override val updateTaskUseCase: UpdateTaskUseCase
        get() = _updateTaskUseCase

    override val deleteTaskUseCase: DeleteTaskUseCase
        get() = _deleteTaskUseCase

    override val logoutUseCase: LogoutUseCase
        get() = _logoutUseCase

    override val taskRouter: TaskRouter
        get() = _taskRouter

    override fun createLoginNavigator(activity: AppCompatActivity): Navigator =
        LoginNavigator(activity)
}