package ru.ftc.todoapp.task.domain

import ru.ftc.todoapp.task.domain.entity.Task

interface GetTasksUseCase {

    operator fun invoke(): List<Task>
}

class GetTasksUseCaseImpl(
    private val taskRepository: TaskRepository
): GetTasksUseCase {

    override fun invoke(): List<Task> =
        taskRepository.get()
}