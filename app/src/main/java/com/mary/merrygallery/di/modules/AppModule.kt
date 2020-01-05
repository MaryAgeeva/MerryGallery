package com.mary.merrygallery.di.modules

import com.mary.data.repositories.GalleryRepository
import com.mary.domain.repositories.IGalleryRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface AppModule {

    @Binds
    @Singleton
    fun bindRepository(repository: GalleryRepository) : IGalleryRepository
}