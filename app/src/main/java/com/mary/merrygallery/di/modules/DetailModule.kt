package com.mary.merrygallery.di.modules

import com.mary.domain.use_cases.detail_page.GetImagesUseCase
import com.mary.domain.use_cases.detail_page.IGetImagesUseCase
import com.mary.merrygallery.di.scopes.DetailScope
import dagger.Binds
import dagger.Module

@Module
interface DetailModule {

    @Binds
    @DetailScope
    fun bindInteractor(interactor: GetImagesUseCase) : IGetImagesUseCase
}