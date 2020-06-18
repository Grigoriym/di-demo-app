package ru.ftc.todoapp.core.navigation

interface Router {

    fun goto(destination: Destination)

    fun setNavigator(navigator: Navigator?)
}