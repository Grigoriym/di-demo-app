package ru.ftc.todoapp.feature.task.domain.entity

import java.io.Serializable
import java.util.*

data class Task(val id: UUID? = null, val description: String = "") : Serializable {
    val isEmpty: Boolean
        get() = id == null
}

