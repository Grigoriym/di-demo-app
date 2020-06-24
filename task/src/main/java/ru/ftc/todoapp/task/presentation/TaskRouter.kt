package ru.ftc.todoapp.task.presentation

import androidx.fragment.app.FragmentActivity
import ru.ftc.todoapp.task.R
import ru.ftc.todoapp.task.api.LoginOpener
import ru.ftc.todoapp.task.domain.entity.Task
import ru.ftc.todoapp.task.ui.TaskFragment

class TaskRouter(
    private val activity: FragmentActivity,
    private val loginOpener: LoginOpener
) {

    fun back() {
        if (activity.supportFragmentManager.backStackEntryCount > 1) {
            activity.supportFragmentManager.popBackStack()
        } else {
            activity.onBackPressed()
        }
    }

    fun openNewTask() {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.content_frame, TaskFragment.newInstance(null))
            .addToBackStack(null)
            .commit()
    }

    fun openEditTask(task: Task) {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.content_frame, TaskFragment.newInstance(task))
            .addToBackStack(null)
            .commit()
    }

    fun openLogin() {
        loginOpener.openLogin(activity)
    }
}