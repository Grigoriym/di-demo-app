package ru.ftc.todoapp.feature.main.di

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.factory
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import ru.ftc.todoapp.feature.login.ui.LoginActivity
import ru.ftc.todoapp.feature.task.domain.entity.Task
import ru.ftc.todoapp.feature.task.ui.TaskFragment
import ru.ftc.todoapp.navigation.Destination

val mainNavigatorKodeinModule = Kodein.Module("mainNavigatorKodeinModule") {

    bind<Fragment>() with factory { destination: Destination ->
        return@factory when (destination) {

            Destination.NewTask -> {
                TaskFragment.newInstance(Task())
            }

            is Destination.EditTask -> {
                TaskFragment.newInstance(destination.task)
            }

            else -> {
                throw IllegalArgumentException("cannot navigate to $destination")
            }

        }
    }

    bind<Intent>() with provider {
        Intent(
            instance<AppCompatActivity>(),
            LoginActivity::class.java
        )
    }
}