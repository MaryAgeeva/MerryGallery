package com.mary.merrygallery

import android.app.Application
import com.mary.merrygallery.di.components.AppComponent
import com.mary.merrygallery.di.components.DaggerAppComponent
import com.squareup.leakcanary.LeakCanary

class MerryGalleryApp : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .context(this)
            .build()
        if(LeakCanary.isInAnalyzerProcess(this)) return
        LeakCanary.install(this)
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}