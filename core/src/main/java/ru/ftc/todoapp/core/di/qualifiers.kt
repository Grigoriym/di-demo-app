package ru.ftc.todoapp.core.di

import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue

val appQualifier: Qualifier = object : Qualifier {

    override val value: QualifierValue
        get() = toString()
}
