package ru.ftc.todoapp.navigation

import android.app.Activity
import android.content.Intent
import ru.ftc.todoapp.core.navigation.Destination
import ru.ftc.todoapp.core.navigation.Navigator
import ru.ftc.todoapp.core.navigation.TaskList
import ru.ftc.todoapp.login.navigation.Back
import ru.ftc.todoapp.login.ui.LoginActivity
import ru.ftc.todoapp.task.ui.TaskActivity

class AppNavigator(private val activity: Activity) : Navigator {

    override fun navigateTo(destination: Destination): Boolean {
        when (destination) {
            is TaskList -> {
                activity.startActivity(Intent(activity, TaskActivity::class.java))
                activity.finish()
            }
            is Login -> {
                activity.startActivity(Intent(activity, LoginActivity::class.java))
                activity.finish()
            }
            Back -> activity.onBackPressed()
            else -> return false
        }
        return true
    }
}
