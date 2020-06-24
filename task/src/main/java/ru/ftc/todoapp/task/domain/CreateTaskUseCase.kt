package ru.ftc.todoapp.task.domain

import dagger.Reusable
import ru.ftc.todoapp.task.data.TaskRepository
import ru.ftc.todoapp.task.domain.entity.Task
import java.util.*
import javax.inject.Inject

@Reusable
class CreateTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {

    operator fun invoke(description: String) {
        val task = Task(
            id = UUID.randomUUID(),
            description = description
        )
        taskRepository.add(task)
    }
}