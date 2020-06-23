package ru.ftc.todoapp.core.navigation

import java.util.*

interface Destination

object TaskList : Destination

object Back : Destination

object NewTask : Destination

object Login : Destination

data class EditTask(val taskId: UUID) : Destination
