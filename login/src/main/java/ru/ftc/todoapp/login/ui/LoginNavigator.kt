package ru.ftc.todoapp.login.ui

import android.app.Activity
import ru.ftc.todoapp.core.navigation.Destination
import ru.ftc.todoapp.core.navigation.Navigator
import ru.ftc.todoapp.login.navigation.Back

class LoginNavigator(
    private val activity: Activity,
    private val appNavigator: Navigator
) : Navigator {

    override fun navigateTo(destination: Destination): Boolean {
        when (destination) {
            Back -> activity.onBackPressed()
            else -> {
                if (!appNavigator.navigateTo(destination)) {
                    throw IllegalArgumentException("cannot navigate to $destination")
                }
            }
        }
        return true
    }
}
