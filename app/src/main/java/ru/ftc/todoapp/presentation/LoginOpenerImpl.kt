package ru.ftc.todoapp.presentation

import android.app.Activity
import android.content.Intent
import ru.ftc.todoapp.login.ui.LoginActivity
import ru.ftc.todoapp.task.api.LoginOpener
import javax.inject.Inject

internal class LoginOpenerImpl @Inject constructor() : LoginOpener {

    override fun openLogin(activity: Activity) {
        activity.startActivity(Intent(activity, LoginActivity::class.java))
        activity.finish()
    }
}