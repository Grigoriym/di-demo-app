package ru.ftc.todoapp.task.di

import androidx.fragment.app.FragmentActivity
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import ru.ftc.todoapp.core.di.appQualifier
import ru.ftc.todoapp.core.navigation.Navigator
import ru.ftc.todoapp.task.data.TaskRepositoryImpl
import ru.ftc.todoapp.task.domain.*
import ru.ftc.todoapp.task.domain.entity.Task
import ru.ftc.todoapp.task.presentation.*
import ru.ftc.todoapp.task.ui.TaskNavigator

val taskModules = module {

    single<TaskRepository> {
        TaskRepositoryImpl(storage = get())
    }

    factory<GetTasksUseCase> {
        GetTasksUseCaseImpl(taskRepository = get())
    }

    factory<CreateTaskUseCase> {
        CreateTaskUseCaseImpl(taskRepository = get())
    }

    factory<DeleteTaskUseCase> {
        DeleteTaskUseCaseImpl(taskRepository = get())
    }

    factory<UpdateTaskUseCase> {
        UpdateTaskUseCaseImpl(taskRepository = get())
    }

    factory<TaskRouter> {
        TaskRouterImpl(router = get())
    }

    scope(taskQualifier) {
        scoped { (activity: FragmentActivity) ->
            TaskNavigator(activity, get(appQualifier) { parametersOf(activity) })
        }

        scoped<TaskListPresenter> {
            TaskListPresenterImpl(
                getTasksUseCase = get(),
                deleteTaskUseCase = get(),
                logoutUseCase = get(),
                router = get()
            )
        }

        scoped<TaskPresenter> { (task: Task) ->
            TaskPresenterImpl(
                createTaskUseCase = get(),
                updateTaskUseCase = get(),
                router = get(),
                task = task
            )
        }

        scoped<Navigator> { (activity: FragmentActivity) ->
            TaskNavigator(activity, get(appQualifier) { parametersOf(activity) })
        }
    }
}
