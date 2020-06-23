package ru.ftc.todoapp.login.presentation

import ru.ftc.todoapp.core.navigation.Router
import ru.ftc.todoapp.core.navigation.TaskList

class LoginRouterImpl(private val router: Router) : LoginRouter {

    override fun openTaskList() {
        router.goto(TaskList)
    }
}
