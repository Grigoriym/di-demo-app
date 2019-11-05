package ru.ftc.todoapp.feature.main

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.direct
import org.kodein.di.generic.instance
import ru.ftc.todoapp.R
import ru.ftc.todoapp.feature.main.di.mainNavigatorKodeinModule
import ru.ftc.todoapp.navigation.Destination
import ru.ftc.todoapp.navigation.Navigator


class MainNavigator(activityKodein: Kodein) : Navigator, KodeinAware {
    override val kodein = Kodein {
        extend(activityKodein)
        import(mainNavigatorKodeinModule)
    }

    override fun navigateTo(destination: Destination) {
        val activity: AppCompatActivity by instance()
        if (destination == Destination.Login) {
            activity.startActivity(direct.instance())
            activity.finish()
            return
        }

        if (destination == Destination.Back) {
            backPressed(activity)
        } else {
            navigateToList(destination, activity)
        }
    }

    private fun navigateToList(
        destination: Destination,
        activity: AppCompatActivity
    ) {
        val navigateFragment: Fragment by instance(arg = destination)
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.content_frame, navigateFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun backPressed(activity: AppCompatActivity) {
        if (activity.supportFragmentManager.backStackEntryCount > 1) {
            activity.supportFragmentManager.popBackStack()
        } else {
            activity.onBackPressed()
        }
    }
}