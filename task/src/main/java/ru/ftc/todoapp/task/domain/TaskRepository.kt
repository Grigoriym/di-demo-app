package ru.ftc.todoapp.task.domain

import ru.ftc.todoapp.task.domain.entity.Task

interface TaskRepository {

    fun get(): List<Task>

    fun add(task: Task)

    fun update(task: Task)

    fun delete(task: Task)
}