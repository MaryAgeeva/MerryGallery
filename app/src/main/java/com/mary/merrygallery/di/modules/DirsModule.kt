package com.mary.merrygallery.di.modules

import com.mary.domain.use_cases.dirs_page.GetDirectoriesUseCase
import com.mary.domain.use_cases.dirs_page.IGetDirectoriesUseCase
import com.mary.merrygallery.di.scopes.DirsScope
import dagger.Binds
import dagger.Module

@Module
interface DirsModule {

    @Binds
    @DirsScope
    fun bindInteractor(interactor: GetDirectoriesUseCase) : IGetDirectoriesUseCase
}