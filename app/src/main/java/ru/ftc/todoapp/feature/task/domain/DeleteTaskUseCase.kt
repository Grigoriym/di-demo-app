package ru.ftc.todoapp.feature.task.domain

import ru.ftc.todoapp.feature.task.domain.entity.Task

interface DeleteTaskUseCase {

    operator fun invoke(task: Task)
}

class DeleteTaskUseCaseImpl(
    private val taskRepository: TaskRepository
): DeleteTaskUseCase {

    override fun invoke(task: Task) {
        taskRepository.delete(task)
    }
}