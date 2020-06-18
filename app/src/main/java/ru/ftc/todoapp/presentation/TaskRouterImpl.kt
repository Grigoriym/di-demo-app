package ru.ftc.todoapp.presentation

import ru.ftc.todoapp.core.navigation.Router
import ru.ftc.todoapp.navigation.Back
import ru.ftc.todoapp.navigation.EditTask
import ru.ftc.todoapp.navigation.Login
import ru.ftc.todoapp.navigation.NewTask
import ru.ftc.todoapp.task.domain.entity.Task
import ru.ftc.todoapp.task.presentation.TaskRouter

class TaskRouterImpl(private val router: Router) : TaskRouter  {

    override fun back() {
        router.goto(Back)
    }

    override fun openNewTask() {
        router.goto(NewTask)
    }

    override fun openEditTask(task: Task) {
        router.goto(EditTask(task))
    }

    override fun openLogin() {
        router.goto(Login)
    }
}