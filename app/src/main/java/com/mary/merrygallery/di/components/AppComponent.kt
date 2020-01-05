package com.mary.merrygallery.di.components

import android.content.Context
import com.mary.merrygallery.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    fun addDirs() : DirsComponent.Builder
    fun addDetail() : DetailComponent.Builder

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context) : Builder

        fun build() : AppComponent
    }
}