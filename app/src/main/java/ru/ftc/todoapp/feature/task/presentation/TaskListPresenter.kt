package ru.ftc.todoapp.feature.task.presentation

import ru.ftc.todoapp.feature.login.domain.LogoutUseCase
import ru.ftc.todoapp.feature.task.domain.DeleteTaskUseCase
import ru.ftc.todoapp.feature.task.domain.GetTasksUseCase
import ru.ftc.todoapp.feature.task.domain.entity.Task
import ru.ftc.todoapp.mvp.Presenter
import ru.ftc.todoapp.navigation.Destination
import ru.ftc.todoapp.navigation.Router

interface TaskClickListener {
    fun onTaskClick(task: Task)
}

interface TaskSwipeOutListener {
    fun onTaskSwipe(task: Task)
}

interface AddTaskListener {
    fun onAddClick()
}

abstract class TaskListPresenter : Presenter<TaskListView>(),
    TaskClickListener,
    TaskSwipeOutListener,
    AddTaskListener {

    abstract fun exit()
}

class TaskListPresenterImpl(
    private val getTasksUseCase: GetTasksUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val router: Router
) : TaskListPresenter() {

    override fun onViewAttach() {
        val tasks = getTasksUseCase()
        view?.showTasks(tasks)
    }

    override fun onAddClick() {
        router.goto(Destination.NewTask)
    }

    override fun onTaskClick(task: Task) {
        router.goto(Destination.EditTask(task))
    }

    override fun onTaskSwipe(task: Task) {
        deleteTaskUseCase(task)
    }

    override fun exit() {
        logoutUseCase()
        router.goto(Destination.Login)
    }
}