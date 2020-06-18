package ru.ftc.todoapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.ftc.todoapp.R
import ru.ftc.todoapp.core.di.CoreDependency
import ru.ftc.todoapp.task.ui.TaskListFragment

class MainActivity : AppCompatActivity() {

    // FIXME Dependencies from App
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

    override fun onResumeFragments() {
        super.onResumeFragments()
        // FIXME Providing dependencies from App
        coreDependency.router.setNavigator(MainNavigator(this))
    }

    override fun onPause() {
        super.onPause()
        // FIXME Providing dependencies from App
        coreDependency.router.setNavigator(null)
    }
}