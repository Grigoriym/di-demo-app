package ru.ftc.todoapp.feature.task.presentation

import ru.ftc.todoapp.feature.task.domain.entity.Task
import ru.ftc.todoapp.mvp.View

interface TaskListView: View {

    fun showTasks(tasks: List<Task>)
}