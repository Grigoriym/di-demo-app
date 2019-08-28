package ru.ftc.todoapp.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.ftc.todoapp.R
import ru.ftc.todoapp.feature.task.ui.TaskFragment
import ru.ftc.todoapp.feature.task.ui.TaskListFragment
import ru.ftc.todoapp.navigation.Destination
import ru.ftc.todoapp.navigation.Router

class MainActivity : AppCompatActivity(), Router {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.content_frame, TaskListFragment.newInstance())
                .commit()
        }
    }

    override fun goto(destination: Destination) {
        val newFragment = when (destination) {
            is Destination.NewTask -> TaskFragment.newInstance(null)
            is Destination.EditTask -> TaskFragment.newInstance(destination.task)
            Destination.Back -> null
        }

        if (newFragment == null) {
            supportFragmentManager.popBackStack()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame, newFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}