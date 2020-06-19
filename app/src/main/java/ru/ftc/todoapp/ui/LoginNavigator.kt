package ru.ftc.todoapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import ru.ftc.todoapp.core.navigation.Destination
import ru.ftc.todoapp.core.navigation.Navigator
import ru.ftc.todoapp.navigation.Back
import ru.ftc.todoapp.navigation.TaskList
import ru.ftc.todoapp.task.ui.TaskActivity

class LoginNavigator(private val activity: AppCompatActivity) : Navigator {

    override fun navigateTo(destination: Destination) {
        when (destination) {

            TaskList -> {
                activity.startActivity(Intent(activity.application, TaskActivity::class.java))
                activity.finish()
            }

            Back -> {
                activity.onBackPressed()
            }

            else -> {
                throw IllegalArgumentException("cannot navigate to $destination")
            }
        }
    }
}