package ru.ftc.todoapp.task.presentation

import ru.ftc.todoapp.core.mvp.Presenter
import ru.ftc.todoapp.task.domain.CreateTaskUseCase
import ru.ftc.todoapp.task.domain.UpdateTaskUseCase
import ru.ftc.todoapp.task.domain.entity.Task
import javax.inject.Inject

class TaskPresenter @Inject constructor(
    private val createTaskUseCase: CreateTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val router: TaskRouter,
    private val task: Task?
) :  Presenter<TaskView>() {

    override fun onViewAttach() {
        if (task != null) {
            view?.showTask(task)
        }
    }

    fun onSaveClick(description: String) {
        if (task != null) {
            updateTaskUseCase(task.copy(description = description))
        } else {
            createTaskUseCase(description)
        }
        router.back()
    }

    fun onBackClick() {
        router.back()
    }
}