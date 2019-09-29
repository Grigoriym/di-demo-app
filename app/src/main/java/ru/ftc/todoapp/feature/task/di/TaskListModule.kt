package ru.ftc.todoapp.feature.task.di

import dagger.Binds
import dagger.Module
import ru.ftc.todoapp.feature.login.domain.LogoutUseCase
import ru.ftc.todoapp.feature.login.domain.LogoutUseCaseImpl
import ru.ftc.todoapp.feature.task.domain.DeleteTaskUseCase
import ru.ftc.todoapp.feature.task.domain.DeleteTaskUseCaseImpl
import ru.ftc.todoapp.feature.task.domain.GetTasksUseCase
import ru.ftc.todoapp.feature.task.domain.GetTasksUseCaseImpl
import ru.ftc.todoapp.feature.task.presentation.TaskListPresenter
import ru.ftc.todoapp.feature.task.presentation.TaskListPresenterImpl

@Module
interface TaskListModule {

    @Binds
    fun bindLogoutUseCase(impl: LogoutUseCaseImpl): LogoutUseCase

    @Binds
    fun bindGetTasksUseCase(impl: GetTasksUseCaseImpl): GetTasksUseCase

    @Binds
    fun bindDeleteTaskUseCase(impl: DeleteTaskUseCaseImpl): DeleteTaskUseCase

    @Binds
    fun bindTaskListPresenter(impl: TaskListPresenterImpl): TaskListPresenter
}