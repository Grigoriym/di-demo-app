package ru.ftc.todoapp.task.domain

import ru.ftc.todoapp.task.data.TaskRepository
import ru.ftc.todoapp.task.domain.entity.Task
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {

    operator fun invoke(): List<Task> =
        taskRepository.get()
}