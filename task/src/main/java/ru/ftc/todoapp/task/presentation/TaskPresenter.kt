package ru.ftc.todoapp.task.presentation

import ru.ftc.todoapp.core.mvp.Presenter
import ru.ftc.todoapp.task.domain.CreateTaskUseCase
import ru.ftc.todoapp.task.domain.UpdateTaskUseCase
import ru.ftc.todoapp.task.domain.entity.Task

abstract class TaskPresenter : Presenter<TaskView>() {

    abstract fun onSaveClick(description: String)

    abstract fun onBackClick()
}

class TaskPresenterImpl(
    private val createTaskUseCase: CreateTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val router: TaskRouter,
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
        router.back()
    }

    override fun onBackClick() {
        router.back()
    }
}