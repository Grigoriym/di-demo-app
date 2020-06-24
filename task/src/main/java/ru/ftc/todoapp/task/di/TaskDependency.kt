package ru.ftc.todoapp.task.di

import androidx.appcompat.app.AppCompatActivity
import ru.ftc.todoapp.core.navigation.Navigator
import ru.ftc.todoapp.login.repo.domain.LogoutUseCase
import ru.ftc.todoapp.task.api.LoginOpener
import ru.ftc.todoapp.task.domain.CreateTaskUseCase
import ru.ftc.todoapp.task.domain.DeleteTaskUseCase
import ru.ftc.todoapp.task.domain.GetTasksUseCase
import ru.ftc.todoapp.task.domain.UpdateTaskUseCase

interface TaskDependency {

    val getTasksUseCase: GetTasksUseCase

    val createTaskUseCase: CreateTaskUseCase

    val updateTaskUseCase: UpdateTaskUseCase

    val deleteTaskUseCase: DeleteTaskUseCase

    val logoutUseCase: LogoutUseCase

    val loginOpener: LoginOpener

    fun createTaskNavigator(activity: AppCompatActivity): Navigator
}