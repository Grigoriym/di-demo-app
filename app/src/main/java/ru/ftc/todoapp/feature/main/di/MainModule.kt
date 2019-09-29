package ru.ftc.todoapp.feature.main.di

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.ftc.todoapp.app.di.scope.FragmentScope
import ru.ftc.todoapp.feature.task.data.TaskRepositoryImpl
import ru.ftc.todoapp.feature.task.di.TaskListModule
import ru.ftc.todoapp.feature.task.di.TaskModule
import ru.ftc.todoapp.feature.task.domain.TaskRepository
import ru.ftc.todoapp.feature.task.ui.TaskFragment
import ru.ftc.todoapp.feature.task.ui.TaskListFragment

@Module
interface MainModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [TaskListModule::class])
    fun taskList(): TaskListFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [TaskModule::class])
    fun task(): TaskFragment

    @Binds
    fun bindTaskRepository(impl: TaskRepositoryImpl): TaskRepository
}