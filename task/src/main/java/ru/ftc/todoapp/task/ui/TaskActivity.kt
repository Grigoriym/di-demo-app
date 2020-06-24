package ru.ftc.todoapp.task.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.ftc.todoapp.task.R

class TaskActivity : AppCompatActivity() {

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