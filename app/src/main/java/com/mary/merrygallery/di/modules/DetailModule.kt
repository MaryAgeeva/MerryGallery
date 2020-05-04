package com.mary.merrygallery.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mary.domain.use_cases.detail_page.GetImagesUseCase
import com.mary.domain.use_cases.detail_page.IGetImagesUseCase
import com.mary.merrygallery.di.modules.view_model.ViewModelFactory
import com.mary.merrygallery.di.modules.view_model.ViewModelKey
import com.mary.merrygallery.di.scopes.DetailScope
import com.mary.merrygallery.presentation.detail_page.DetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Named

@Module
interface DetailModule {

    @Binds
    @DetailScope
    @Named(value = DETAIL_VIEW_MODEL_NAME)
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @DetailScope
    @ViewModelKey(DetailViewModel::class)
    fun bindDetailViewModel(viewModel: DetailViewModel) : ViewModel


    @Binds
    @DetailScope
    fun bindInteractor(interactor: GetImagesUseCase) : IGetImagesUseCase
}