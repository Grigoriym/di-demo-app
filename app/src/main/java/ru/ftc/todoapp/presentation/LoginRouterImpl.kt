package ru.ftc.todoapp.presentation

import ru.ftc.todoapp.core.navigation.Router
import ru.ftc.todoapp.login.presentation.LoginRouter
import ru.ftc.todoapp.navigation.TaskList

class LoginRouterImpl(private val router: Router) : LoginRouter {

    override fun openTaskList() {
        router.goto(TaskList)
    }
}