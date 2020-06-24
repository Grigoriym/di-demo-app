package ru.ftc.todoapp.core.gson

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
annotation class GsonScope

@Module
object GsonModule {
    @GsonScope
    @Provides
    fun gson() = Gson()
}