package ru.ftc.todoapp.task.presentation

import ru.ftc.todoapp.core.mvp.Presenter
import ru.ftc.todoapp.login.repo.domain.LogoutUseCase
import ru.ftc.todoapp.task.di.TaskScope
import ru.ftc.todoapp.task.domain.DeleteTaskUseCase
import ru.ftc.todoapp.task.domain.GetTasksUseCase
import ru.ftc.todoapp.task.domain.entity.Task
import javax.inject.Inject

@TaskScope
class TaskListPresenter @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val router: TaskRouter
) : Presenter<TaskListView>() {

    override fun onViewAttach() {
        val tasks = getTasksUseCase()
        view?.showTasks(tasks)
    }

    fun onAddClick() {
        router.openNewTask()
    }

    fun onTaskClick(task: Task) {
        router.openEditTask(task)
    }

    fun onTaskSwipe(task: Task) {
        deleteTaskUseCase(task)
    }

    fun exit() {
        logoutUseCase()
        router.openLogin()
    }
}