package ru.ftc.todoapp.presentation

import android.app.Activity
import android.content.Intent
import ru.ftc.todoapp.login.api.TaskListOpener
import ru.ftc.todoapp.task.ui.TaskActivity
import javax.inject.Inject

internal class TaskListOpenerImpl @Inject constructor() : TaskListOpener {

    override fun openTaskList(activity: Activity) {
        activity.startActivity(Intent(activity.application, TaskActivity::class.java))
        activity.finish()
    }
}