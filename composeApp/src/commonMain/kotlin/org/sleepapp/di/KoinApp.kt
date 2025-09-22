package org.sleepapp.di

import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.includes
import org.koin.dsl.module
import org.sleepapp.data.repository.NoteRepository

fun initKoin(config:KoinAppDeclaration? = null){
    startKoin{
        config?.invoke(this)
        printLogger()
        includes(config)
        modules(appModule)
    }
}