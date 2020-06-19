package ru.ftc.todoapp.task.domain

import ru.ftc.todoapp.task.domain.entity.Task

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