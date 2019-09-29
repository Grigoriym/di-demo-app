package ru.ftc.todoapp.feature.task.domain

import ru.ftc.todoapp.feature.task.domain.entity.Task
import java.util.*
import javax.inject.Inject

interface CreateTaskUseCase {

    operator fun invoke(description: String)
}

class CreateTaskUseCaseImpl @Inject constructor(
    private val taskRepository: TaskRepository
): CreateTaskUseCase {

    override fun invoke(description: String) {
        val task = Task(
            id = UUID.randomUUID(),
            description = description
        )
        taskRepository.add(task)
    }
}