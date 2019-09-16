package ru.ftc.todoapp.app.sl

interface Provider<T> {

    fun get(serviceLocator: ServiceLocator): T
}