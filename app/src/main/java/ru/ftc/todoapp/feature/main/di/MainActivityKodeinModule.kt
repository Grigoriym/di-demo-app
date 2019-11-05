package ru.ftc.todoapp.feature.main.di

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import ru.ftc.todoapp.feature.main.MainNavigator
import ru.ftc.todoapp.feature.task.ui.TaskListFragment
import ru.ftc.todoapp.navigation.Navigator

val mainKodeinModule = Kodein.Module("mainKodeinModule") {
    bind<Navigator>() with singleton { MainNavigator((instance<AppCompatActivity>() as KodeinAware).kodein) }
    bind<Fragment>(tag = "TaskListFragment") with provider { TaskListFragment.newInstance() }
}