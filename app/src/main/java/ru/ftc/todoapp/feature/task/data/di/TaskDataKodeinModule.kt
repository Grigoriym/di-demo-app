package ru.ftc.todoapp.feature.task.data.di

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import ru.ftc.todoapp.feature.task.data.TaskRepositoryImpl
import ru.ftc.todoapp.feature.task.domain.TaskRepository

val taskDataKodeinModule = Kodein.Module("taskDataKodeinModule"){
    bind<TaskRepository>() with singleton { TaskRepositoryImpl(instance()) }
}