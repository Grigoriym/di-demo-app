package ru.ftc.todoapp.app.sl

import ru.ftc.todoapp.app.App
import kotlin.reflect.KClass

interface ServiceLocator {

    fun <T : Any> get(type: KClass<T>): T

    fun add(key: KClass<*>, provider:Provider<*>)
}

inline fun <reified T : Any> ServiceLocator.get(): T = get(T::class)

inline fun <reified T: Any> serviceLocator(): Lazy<T> = lazy { App.serviceLocator.get(T::class) }