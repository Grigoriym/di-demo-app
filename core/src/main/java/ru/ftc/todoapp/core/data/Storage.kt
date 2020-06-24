package ru.ftc.todoapp.core.data

import android.app.Application
import android.content.Context.MODE_PRIVATE
import dagger.Binds
import dagger.Module
import javax.inject.Inject
import javax.inject.Scope

@Scope
annotation class StorageScope

@Module
abstract class StorageModule {
    @Binds
    internal abstract fun bind(storage: StorageImpl): Storage
}

interface Storage {

    operator fun set(key: String, value: String?)

    operator fun get(key: String): String?
}

@StorageScope
internal class StorageImpl @Inject constructor(
    context: Application
) : Storage {

    private companion object {

        const val SP_NAME = "TASKS_SP"
    }

    private val sp = context.getSharedPreferences(SP_NAME, MODE_PRIVATE)

    override fun set(key: String, value: String?) {
        sp.edit().putString(key, value).apply()
    }

    override fun get(key: String): String? =
        sp.getString(key, null)
}