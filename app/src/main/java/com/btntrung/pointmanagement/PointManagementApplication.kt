package com.btntrung.pointmanagement

import android.app.Application
import com.facebook.FacebookSdk
import com.facebook.FacebookSdk.getApplicationSignature
import com.facebook.appevents.AppEventsLogger
import com.orhanobut.hawk.Hawk
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class PointManagementApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Hawk.init(applicationContext).build()

        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)

        Timber.plant(Timber.DebugTree())
        startKoin {
            androidContext(this@PointManagementApplication)
            modules()
        }
    }
}