package ru.ftc.todoapp.task.di

import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.ItemTouchHelper
import com.google.gson.Gson
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import ru.ftc.todoapp.core.data.Storage
import ru.ftc.todoapp.task.api.LoginOpener
import ru.ftc.todoapp.task.domain.entity.Task
import ru.ftc.todoapp.task.ui.TaskFragment
import ru.ftc.todoapp.task.ui.TaskListFragment
import ru.ftc.todoapp.task.ui.TaskListTouchCallback
import javax.inject.Scope

interface TaskDependency {
    interface DepProvider {
        fun taskDependency(): TaskDependency
    }

    fun storage(): Storage
    fun loginOpener(): LoginOpener
    fun gson(): Gson
}

@Scope
internal annotation class TaskScope

@TaskScope
@Component(dependencies = [TaskDependency::class], modules = [TaskModule::class])
internal interface TaskComponent {
    fun inject(me: TaskFragment)
    fun inject(me: TaskListFragment)

    @Component.Factory
    interface F {
        fun create(
            @BindsInstance activity: FragmentActivity,
            @BindsInstance task: Task?,
            dep: TaskDependency
        ): TaskComponent
    }
}

@Module
internal object TaskModule {
    @Provides
    fun provideItemTouchHelper(callback: TaskListTouchCallback) = ItemTouchHelper(callback)
}