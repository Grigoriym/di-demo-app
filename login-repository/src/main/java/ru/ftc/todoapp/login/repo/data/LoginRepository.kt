package ru.ftc.todoapp.login.repo.data

import ru.ftc.todoapp.core.data.Storage
import javax.inject.Inject

class LoginRepository @Inject constructor(private val storage: Storage)  {

    private companion object {
        const val LOGGED_IN_KEY = "LOGGED_IN_KEY"
        const val LOGGED_IN = "LOGGED_IN"
    }

    fun isLoggedIn(): Boolean =
        storage[LOGGED_IN_KEY] == LOGGED_IN

    fun login(name: String, password: String): Boolean {
        val loggedIn = name == "1" && password == "1"
        storage[LOGGED_IN_KEY] = if (loggedIn) LOGGED_IN else null
        return loggedIn
    }

    fun logout() {
        storage[LOGGED_IN_KEY] = null
    }
}