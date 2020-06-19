package ru.ftc.todoapp.task.domain

import ru.ftc.todoapp.task.domain.entity.Task
import java.util.*

interface CreateTaskUseCase {

    operator fun invoke(description: String)
}

class CreateTaskUseCaseImpl(
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