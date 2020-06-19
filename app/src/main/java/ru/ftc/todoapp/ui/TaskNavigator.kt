package ru.ftc.todoapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import ru.ftc.todoapp.R
import ru.ftc.todoapp.core.navigation.Destination
import ru.ftc.todoapp.core.navigation.Navigator
import ru.ftc.todoapp.login.ui.LoginActivity
import ru.ftc.todoapp.navigation.Back
import ru.ftc.todoapp.navigation.EditTask
import ru.ftc.todoapp.navigation.Login
import ru.ftc.todoapp.navigation.NewTask
import ru.ftc.todoapp.task.ui.TaskFragment

class TaskNavigator(private val activity: AppCompatActivity) : Navigator {

    override fun navigateTo(destination: Destination) {
        if (destination == Login) {
            activity.startActivity(Intent(activity, LoginActivity::class.java))
            activity.finish()
            return
        }

        val newFragment = when (destination) {

            NewTask -> {
                TaskFragment.newInstance(null)
            }

            is EditTask -> {
                TaskFragment.newInstance(destination.task)
            }

            Back -> {
                null
            }

            else -> {
                throw IllegalArgumentException("cannot navigate to $destination")
            }
        }

        if (newFragment == null) {
            if (activity.supportFragmentManager.backStackEntryCount > 1) {
                activity.supportFragmentManager.popBackStack()
            } else {
                activity.onBackPressed()
            }
        } else {
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame, newFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}