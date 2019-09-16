package ru.ftc.todoapp.app.sl

class InstanceProvider<T>(private val instance: T): Provider<T> {

    override fun get(serviceLocator: ServiceLocator): T =
        instance
}