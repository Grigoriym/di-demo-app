package ru.ftc.todoapp.navigation

import ru.ftc.todoapp.core.navigation.Destination
import ru.ftc.todoapp.task.domain.entity.Task

object Back : Destination

object NewTask : Destination

object TaskList : Destination

object Login : Destination

data class EditTask(val task: Task) : Destination