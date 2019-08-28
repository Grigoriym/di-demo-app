package ru.ftc.todoapp.feature.task.domain

import ru.ftc.todoapp.feature.task.domain.entity.Task

interface UpdateTaskUseCase {

    operator fun invoke(task: Task)
}

class UpdateTaskUseCaseImpl(
    private val taskRepository: TaskRepository
) : UpdateTaskUseCase {

    override fun invoke(task: Task) {
        taskRepository.update(task)
    }
}