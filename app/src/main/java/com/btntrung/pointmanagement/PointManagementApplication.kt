package com.btntrung.pointmanagement

import android.app.Application
import com.orhanobut.hawk.Hawk
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PointManagementApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Hawk.init(applicationContext).build()
        startKoin {
            androidContext(this@PointManagementApplication)
            modules()
        }
    }
}