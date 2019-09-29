package ru.ftc.todoapp.feature.main

import android.os.Bundle
import ru.ftc.todoapp.R
import ru.ftc.todoapp.app.BaseActivity
import ru.ftc.todoapp.feature.task.ui.TaskListFragment
import ru.ftc.todoapp.navigation.Router
import javax.inject.Inject

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_frame)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.content_frame, TaskListFragment.newInstance())
                .commit()
        }
    }

    @Inject lateinit var router: Router

    override fun onResumeFragments() {
        super.onResumeFragments()
        router.setNavigator(MainNavigator(this))
    }

    override fun onPause() {
        super.onPause()
        router.setNavigator(null)
    }
}