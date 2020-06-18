package ru.ftc.todoapp.task.presentation

import ru.ftc.todoapp.task.domain.entity.Task

interface TaskRouter {

    fun back()

    fun openNewTask()

    fun openEditTask(task: Task)

    fun openLogin()
}