package com.mary.merrygallery.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mary.domain.use_cases.dirs_page.GetDirectoriesUseCase
import com.mary.domain.use_cases.dirs_page.IGetDirectoriesUseCase
import com.mary.merrygallery.di.modules.view_model.ViewModelFactory
import com.mary.merrygallery.di.modules.view_model.ViewModelKey
import com.mary.merrygallery.di.scopes.DirsScope
import com.mary.merrygallery.presentation.dirs_page.DirsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Named

@Module
interface DirsModule {

    @Binds
    @DirsScope
    @Named(value = DIRS_VIEW_MODEL_NAME)
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @DirsScope
    @ViewModelKey(DirsViewModel::class)
    fun bindDetailViewModel(viewModel: DirsViewModel) : ViewModel

    @Binds
    @DirsScope
    fun bindInteractor(interactor: GetDirectoriesUseCase) : IGetDirectoriesUseCase
}