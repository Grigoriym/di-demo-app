package ru.ftc.todoapp.task.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.parameter.parametersOf
import ru.ftc.todoapp.core.navigation.Navigator
import ru.ftc.todoapp.core.navigation.Router
import ru.ftc.todoapp.task.R
import ru.ftc.todoapp.task.di.taskModules
import ru.ftc.todoapp.task.di.taskQualifier

class TaskActivity : AppCompatActivity() {

    internal val featureScope by lazy {
        getKoin().getOrCreateScope(toString(), taskQualifier)
    }

    private val router: Router by inject()
    private val taskNavigator: Navigator
        get() {
            return featureScope.get<TaskNavigator> {
                parametersOf(this@TaskActivity)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_frame)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.content_frame, TaskListFragment.newInstance())
                .commit()
        }
    }

    override fun onResume() {
        super.onResume()
        router.setNavigator(navigator = taskNavigator)
    }

    override fun onPause() {
        super.onPause()
        router.setNavigator(null)
    }

    companion object {

        init {
            loadKoinModules(taskModules)
        }
    }
}
