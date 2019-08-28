package ru.ftc.todoapp.feature.task.domain.entity

import java.io.Serializable
import java.util.*

data class Task(val id: UUID, val description: String): Serializable