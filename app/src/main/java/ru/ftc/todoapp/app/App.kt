package ru.ftc.todoapp.app

import android.app.Application
import ru.ftc.todoapp.feature.task.data.TaskRepositoryImpl
import ru.ftc.todoapp.feature.task.domain.*

class App: Application() {

    companion object {
        private val taskRepository: TaskRepository = TaskRepositoryImpl()

        val getTasksUseCase: GetTasksUseCase = GetTasksUseCaseImpl(taskRepository)
        val createTaskUseCase: CreateTaskUseCase = CreateTaskUseCaseImpl(taskRepository)
        val deleteTaskUseCase: DeleteTaskUseCase = DeleteTaskUseCaseImpl(taskRepository)
        val updateTaskUseCase: UpdateTaskUseCase = UpdateTaskUseCaseImpl(taskRepository)
    }
}