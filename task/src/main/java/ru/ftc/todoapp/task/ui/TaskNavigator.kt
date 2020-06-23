package ru.ftc.todoapp.task.ui

import androidx.fragment.app.FragmentActivity
import ru.ftc.todoapp.core.navigation.Destination
import ru.ftc.todoapp.core.navigation.Navigator
import ru.ftc.todoapp.navigation.Back
import ru.ftc.todoapp.navigation.EditTask
import ru.ftc.todoapp.navigation.Login
import ru.ftc.todoapp.navigation.NewTask
import ru.ftc.todoapp.task.R

class TaskNavigator(
    private val activity: FragmentActivity,
    private val appNavigator: Navigator
) : Navigator {

    override fun navigateTo(destination: Destination): Boolean {
        if (destination == Login) {
            return appNavigator.navigateTo(destination)
        }

        val newFragment = when (destination) {
            NewTask -> TaskFragment.newInstance(null)
            is EditTask -> TaskFragment.newInstance(destination.task)
            Back -> null
            else -> throw IllegalArgumentException("cannot navigate to $destination")
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
        return true
    }
}
