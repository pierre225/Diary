package com.pithou.diary.di

import com.pithou.diary.data.RecordRepository
import com.pithou.diary.data.persistence.database.RecordDatabase
import com.pithou.diary.ui.home.HomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    single { RecordDatabase.buildDatabase(androidApplication(), "Record.db") }
    factory { get<RecordDatabase>().recordDao() }
    singleOf(::RecordRepository)
    viewModelOf(::HomeViewModel)
}