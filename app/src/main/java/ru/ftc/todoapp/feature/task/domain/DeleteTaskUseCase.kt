package ru.ftc.todoapp.feature.task.domain

import ru.ftc.todoapp.feature.task.domain.entity.Task
import javax.inject.Inject

interface DeleteTaskUseCase {

    operator fun invoke(task: Task)
}

class DeleteTaskUseCaseImpl @Inject constructor(
    private val taskRepository: TaskRepository
): DeleteTaskUseCase {

    override fun invoke(task: Task) {
        taskRepository.delete(task)
    }
}