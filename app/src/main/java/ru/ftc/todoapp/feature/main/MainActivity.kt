package ru.ftc.todoapp.feature.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import ru.ftc.todoapp.R
import ru.ftc.todoapp.feature.main.di.mainKodeinModule
import ru.ftc.todoapp.feature.task.data.di.taskDataKodeinModule
import ru.ftc.todoapp.feature.task.domain.di.taskDomainKodeinModule
import ru.ftc.todoapp.navigation.Navigator
import ru.ftc.todoapp.navigation.Router

class MainActivity : AppCompatActivity(), KodeinAware {
    override lateinit var kodein: Kodein

    private fun initKodein() {
        kodein = Kodein {
            extend((application as KodeinAware).kodein)
            import(mainKodeinModule)
            import(taskDataKodeinModule)
            import(taskDomainKodeinModule)
            bind<AppCompatActivity>() with provider { this@MainActivity }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initKodein()

        setContentView(R.layout.activity_frame)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.content_frame, direct.instance<Fragment>("TaskListFragment"))
                .commit()
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        val router: Router by instance()
        router.setNavigator(direct.instance<Navigator>())
    }

    override fun onPause() {
        super.onPause()
        val router: Router by instance()
        router.setNavigator(null)
    }
}