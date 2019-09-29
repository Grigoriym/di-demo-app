package ru.ftc.todoapp.feature.task.domain

import ru.ftc.todoapp.feature.task.domain.entity.Task
import javax.inject.Inject

interface UpdateTaskUseCase {

    operator fun invoke(task: Task)
}

class UpdateTaskUseCaseImpl @Inject constructor(
    private val taskRepository: TaskRepository
) : UpdateTaskUseCase {

    override fun invoke(task: Task) {
        taskRepository.update(task)
    }
}