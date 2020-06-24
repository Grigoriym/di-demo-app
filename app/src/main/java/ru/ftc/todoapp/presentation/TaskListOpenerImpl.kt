package ru.ftc.todoapp.presentation

import android.app.Activity
import android.content.Intent
import ru.ftc.todoapp.login.api.TaskListOpener
import ru.ftc.todoapp.task.ui.TaskActivity

class TaskListOpenerImpl() : TaskListOpener {

    override fun openTaskList(activity: Activity) {
        activity.startActivity(Intent(activity.application, TaskActivity::class.java))
        activity.finish()
    }
}