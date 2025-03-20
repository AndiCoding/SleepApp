package org.sleepapp.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.sleepapp.viewmodel.AlarmViewModel
import org.sleepapp.viewmodel.StatisticsViewModel
import org.sleepapp.viewmodel.NotesViewModel
import org.sleepapp.viewmodel.ProfileViewModel


import org.sleepapp.getPlatform

val appModule = module {

    factory { getPlatform(this) }
    viewModelOf(::AlarmViewModel)
    viewModelOf(::StatisticsViewModel)
    viewModelOf(::NotesViewModel)
    viewModelOf(::ProfileViewModel)



}