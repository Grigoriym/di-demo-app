package ru.ftc.todoapp.task.data

import com.google.gson.Gson
import ru.ftc.todoapp.core.data.Storage
import ru.ftc.todoapp.task.domain.entity.Task
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val storage: Storage,
    private val gson: Gson
) {

    private companion object {

        const val TASKS_KEY = "TASKS_KEY"
    }

    fun get(): List<Task> =
        gson.fromJson(storage[TASKS_KEY], Array<Task>::class.java)?.toList() ?: listOf()

    fun add(task: Task) {
        val tasks = get().toMutableList()
        tasks.add(task)
        storage[TASKS_KEY] = gson.toJson(tasks.toTypedArray())
    }

    fun update(task: Task) {
        val tasks = get().toMutableList()
        val position = tasks.indexOfFirst { it.id == task.id }
        tasks[position] = task
        storage[TASKS_KEY] = gson.toJson(tasks.toTypedArray())
    }

    fun delete(task: Task) {
        val tasks = get().toMutableList()
        tasks.remove(task)
        storage[TASKS_KEY] = gson.toJson(tasks.toTypedArray())
    }
}