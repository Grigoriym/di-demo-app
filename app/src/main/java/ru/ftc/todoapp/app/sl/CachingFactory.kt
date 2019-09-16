package ru.ftc.todoapp.app.sl

class CachingFactory<T>(
    private val creator: ServiceLocator.() -> T
) : Provider<T> {

    private var instance: T? = null

    override fun get(serviceLocator: ServiceLocator): T {
        synchronized(this) {
            instance?.let { return it }
            instance = creator(serviceLocator)
            return instance!!
        }
    }
}