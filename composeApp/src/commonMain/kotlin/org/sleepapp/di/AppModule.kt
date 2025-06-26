package org.sleepapp.di


import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.sleepapp.data.database.CreateDatabase
import org.sleepapp.data.database.RoomDb
import org.sleepapp.data.repository.AlarmRepository
import org.sleepapp.getPlatform
import org.sleepapp.viewmodel.AlarmViewModel
import org.sleepapp.viewmodel.NotesViewModel
import org.sleepapp.viewmodel.ProfileViewModel
import org.sleepapp.viewmodel.StatisticsViewModel

val appModule = module {

    factory { getPlatform(this) }

    //Database
    single<RoomDb> { CreateDatabase(get()).getDatabase() }

    // Repository
    singleOf(::AlarmRepository)


    // ViewModels
    viewModelOf(::AlarmViewModel)
    viewModelOf(::StatisticsViewModel)
    viewModelOf(::NotesViewModel)
    viewModelOf(::ProfileViewModel)

}