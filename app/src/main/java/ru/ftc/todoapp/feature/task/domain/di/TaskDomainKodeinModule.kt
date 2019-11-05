package ru.ftc.todoapp.feature.task.domain.di

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import ru.ftc.todoapp.feature.task.domain.*

val taskDomainKodeinModule = Kodein.Module("taskDomainKodeinModule"){
    bind<CreateTaskUseCase>() with singleton { CreateTaskUseCaseImpl(instance()) }
    bind<DeleteTaskUseCase>() with singleton { DeleteTaskUseCaseImpl(instance()) }
    bind<GetTasksUseCase>() with singleton { GetTasksUseCaseImpl(instance()) }
    bind<UpdateTaskUseCase>() with singleton { UpdateTaskUseCaseImpl(instance()) }

}