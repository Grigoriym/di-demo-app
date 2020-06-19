package ru.ftc.todoapp.task.presentation

import ru.ftc.todoapp.task.domain.entity.Task
import ru.ftc.todoapp.core.mvp.View

interface TaskView: View {

    fun showTask(task: Task)
}