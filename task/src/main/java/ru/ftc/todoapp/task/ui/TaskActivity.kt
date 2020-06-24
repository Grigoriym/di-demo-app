package ru.ftc.todoapp.task.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.ftc.todoapp.core.di.CoreDependency
import ru.ftc.todoapp.task.R
import ru.ftc.todoapp.task.di.TaskDependency

class TaskActivity : AppCompatActivity() {

    // FIXME Dependencies from App
    private val taskDependency: TaskDependency
        get() = application as TaskDependency

    private val coreDependency: CoreDependency
        get() = application as CoreDependency

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_frame)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.content_frame, TaskListFragment.newInstance())
                .commit()
        }
    }
}