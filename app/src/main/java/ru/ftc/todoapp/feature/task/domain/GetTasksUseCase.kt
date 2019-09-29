package ru.ftc.todoapp.feature.task.domain

import ru.ftc.todoapp.feature.task.domain.entity.Task
import javax.inject.Inject

interface GetTasksUseCase {

    operator fun invoke(): List<Task>
}

class GetTasksUseCaseImpl @Inject constructor(
    private val taskRepository: TaskRepository
): GetTasksUseCase {

    override fun invoke(): List<Task> =
        taskRepository.get()
}