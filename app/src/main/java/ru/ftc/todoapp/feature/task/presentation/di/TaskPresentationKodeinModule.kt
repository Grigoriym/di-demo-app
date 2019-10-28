package ru.ftc.todoapp.feature.task.presentation.di

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import ru.ftc.todoapp.feature.task.presentation.*

val taskPresentationKodeinModule = Kodein.Module("taskPresentationKodeinModule") {

    bind<TaskPresenter>() with singleton {
        TaskPresenterImpl(
            instance(),
            instance(),
            instance(),
            instance()
        )
    }
}

val taskListPresentationKodeinModule = Kodein.Module("taskListPresentationKodeinModule"){
    bind<TaskListPresenter>() with singleton {
        TaskListPresenterImpl(
            instance(),
            instance(),
            instance(),
            instance()
        )
    }

    bind<TaskClickListener>() with provider { instance<TaskListPresenter>() }
    bind<TaskSwipeOutListener>() with provider { instance<TaskListPresenter>() }

}