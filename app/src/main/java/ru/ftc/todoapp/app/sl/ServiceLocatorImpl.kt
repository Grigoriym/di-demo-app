package ru.ftc.todoapp.app.sl

import kotlin.reflect.KClass

class ServiceLocatorImpl(
    vararg providers: Pair<KClass<*>, Provider<*>>
) : ServiceLocator {

    private val registry: MutableMap<KClass<*>, Provider<*>> = providers.toMap().toMutableMap()

    override fun <T : Any> get(type: KClass<T>): T =
        registry.getValue(type).get(this) as T

    override fun add(key: KClass<*>, provider: Provider<*>) {
        registry[key] = provider
    }
}