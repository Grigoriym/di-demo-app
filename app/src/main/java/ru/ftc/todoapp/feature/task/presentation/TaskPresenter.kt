package ru.ftc.todoapp.feature.task.presentation

import ru.ftc.todoapp.feature.task.domain.CreateTaskUseCase
import ru.ftc.todoapp.feature.task.domain.UpdateTaskUseCase
import ru.ftc.todoapp.feature.task.domain.entity.Task
import ru.ftc.todoapp.mvp.Presenter
import ru.ftc.todoapp.navigation.Destination
import ru.ftc.todoapp.navigation.Router

abstract class TaskPresenter : Presenter<TaskView>() {

    abstract fun onSaveClick(description: String)

    abstract fun onBackClick()
}

class TaskPresenterImpl(
    private val createTaskUseCase: CreateTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val router: Router,
    private val task: Task?
) : TaskPresenter() {

    override fun onViewAttach() {
        if (task != null) {
            view?.showTask(task)
        }
    }

    override fun onSaveClick(description: String) {
        if (task != null) {
            updateTaskUseCase(task.copy(description = description))
        } else {
            createTaskUseCase(description)
        }
        router.goto(Destination.Back)
    }

    override fun onBackClick() {
        router.goto(Destination.Back)
    }
}