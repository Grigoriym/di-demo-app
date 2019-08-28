package ru.ftc.todoapp.feature.task.data

import ru.ftc.todoapp.feature.task.domain.TaskRepository
import ru.ftc.todoapp.feature.task.domain.entity.Task

class TaskRepositoryImpl() : TaskRepository {

    private val tasks = mutableListOf<Task>()

    override fun get(): List<Task> = tasks

    override fun add(task: Task) {
        tasks.add(task)
    }

    override fun update(task: Task) {
        val position = tasks.indexOfFirst { it.id == task.id }
        tasks[position] = task
    }

    override fun delete(task: Task) {
        tasks.remove(task)
    }
}