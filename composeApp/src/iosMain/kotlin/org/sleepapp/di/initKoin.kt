package org.sleepapp.di

fun initKoin(){
    initKoin {
        modules(iosDatabaseModule, appModule)
    }
}