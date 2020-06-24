package ru.ftc.todoapp.login.presentation

import androidx.fragment.app.FragmentActivity
import ru.ftc.todoapp.login.api.TaskListOpener
import javax.inject.Inject

internal class LoginRouter @Inject constructor(
    private val activity: FragmentActivity,
    private val taskListOpener: TaskListOpener
) {

    fun openTaskList() {
        taskListOpener.openTaskList(activity)
    }
}