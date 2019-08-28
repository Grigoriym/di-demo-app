package ru.ftc.todoapp.feature.task.presentation

import ru.ftc.todoapp.feature.task.domain.entity.Task
import ru.ftc.todoapp.mvp.View

interface TaskView: View {

    fun showTask(task: Task)
}