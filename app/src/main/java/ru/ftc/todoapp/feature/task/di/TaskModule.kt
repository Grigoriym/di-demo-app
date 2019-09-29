package ru.ftc.todoapp.feature.task.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.ftc.todoapp.feature.task.domain.CreateTaskUseCase
import ru.ftc.todoapp.feature.task.domain.CreateTaskUseCaseImpl
import ru.ftc.todoapp.feature.task.domain.UpdateTaskUseCase
import ru.ftc.todoapp.feature.task.domain.UpdateTaskUseCaseImpl
import ru.ftc.todoapp.feature.task.domain.entity.Task
import ru.ftc.todoapp.feature.task.presentation.TaskPresenter
import ru.ftc.todoapp.feature.task.presentation.TaskPresenterImpl
import ru.ftc.todoapp.feature.task.ui.TaskFragment
import ru.ftc.todoapp.feature.task.ui.task

@Module
abstract class TaskModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideTask(taskFragment: TaskFragment): Task? =
            taskFragment.arguments?.task
    }

    @Binds
    abstract fun bindCreateTaskUseCase(impl: CreateTaskUseCaseImpl): CreateTaskUseCase

    @Binds
    abstract fun bindUpdateTaskUseCase(impl: UpdateTaskUseCaseImpl): UpdateTaskUseCase

    @Binds
    abstract fun bindTaskPresenter(impl: TaskPresenterImpl): TaskPresenter
}