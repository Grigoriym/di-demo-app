package ru.ftc.todoapp.app.sl

class Factory<T>(
    private val creator: ServiceLocator.() -> T
): Provider<T> {

    override fun get(serviceLocator: ServiceLocator): T =
        creator(serviceLocator)
}