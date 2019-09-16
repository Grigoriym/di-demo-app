package ru.ftc.todoapp.feature.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.ftc.todoapp.R
import ru.ftc.todoapp.app.App
import ru.ftc.todoapp.app.sl.get
import ru.ftc.todoapp.app.sl.serviceLocator
import ru.ftc.todoapp.feature.task.ui.TaskListFragment
import ru.ftc.todoapp.navigation.Router

class MainActivity : AppCompatActivity() {

    private val router: Router by serviceLocator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_frame)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.content_frame, TaskListFragment.newInstance())
                .commit()
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        router.setNavigator(MainNavigator(this))
    }

    override fun onPause() {
        super.onPause()
        router.setNavigator(null)
    }
}