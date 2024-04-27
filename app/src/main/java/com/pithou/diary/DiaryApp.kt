package com.pithou.diary

import android.app.Application
import com.pithou.diary.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class DiaryApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DiaryApp)
            modules(appModule)
        }
    }

}