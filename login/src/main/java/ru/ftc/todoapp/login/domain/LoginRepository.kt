package ru.ftc.todoapp.login.domain

interface LoginRepository {

    fun isLoggedIn(): Boolean

    fun login(name: String, password: String): Boolean

    fun logout()
}
