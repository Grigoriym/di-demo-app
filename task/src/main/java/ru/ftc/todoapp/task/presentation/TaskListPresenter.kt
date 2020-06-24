package ru.ftc.todoapp.task.presentation

import ru.ftc.todoapp.core.mvp.Presenter
import ru.ftc.todoapp.login.repo.domain.LogoutUseCase
import ru.ftc.todoapp.task.domain.DeleteTaskUseCase
import ru.ftc.todoapp.task.domain.GetTasksUseCase
import ru.ftc.todoapp.task.domain.entity.Task

abstract class TaskListPresenter : Presenter<TaskListView>() {

    abstract fun onAddClick()

    abstract fun onTaskClick(task: Task)

    abstract fun onTaskSwipe(task: Task)

    abstract fun exit()
}

class TaskListPresenterImpl(
    private val getTasksUseCase: GetTasksUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val router: TaskRouter
) : TaskListPresenter() {

    override fun onViewAttach() {
        val tasks = getTasksUseCase()
        view?.showTasks(tasks)
    }

    override fun onAddClick() {
        router.openNewTask()
    }

    override fun onTaskClick(task: Task) {
        router.openEditTask(task)
    }

    override fun onTaskSwipe(task: Task) {
        deleteTaskUseCase(task)
    }

    override fun exit() {
        logoutUseCase()
        router.openLogin()
    }
}