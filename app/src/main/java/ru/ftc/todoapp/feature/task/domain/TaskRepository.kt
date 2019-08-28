package ru.ftc.todoapp.feature.task.domain

import ru.ftc.todoapp.feature.task.domain.entity.Task

interface TaskRepository {

    fun get(): List<Task>

    fun add(task: Task)

    fun update(task: Task)

    fun delete(task: Task)
}