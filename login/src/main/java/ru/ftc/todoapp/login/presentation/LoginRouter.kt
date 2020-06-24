package ru.ftc.todoapp.login.presentation

import androidx.fragment.app.FragmentActivity
import ru.ftc.todoapp.login.api.TaskListOpener

internal class LoginRouter(
    private val appCompatActivity: FragmentActivity,
    private val taskListOpener: TaskListOpener
) {

    fun openTaskList() {
        taskListOpener.openTaskList(appCompatActivity)
    }
}