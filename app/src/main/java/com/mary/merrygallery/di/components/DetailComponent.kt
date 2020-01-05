package com.mary.merrygallery.di.components

import com.mary.merrygallery.di.modules.DetailModule
import com.mary.merrygallery.di.scopes.DetailScope
import com.mary.merrygallery.presentation.detail_page.DetailFragment
import dagger.Subcomponent

@Subcomponent(modules = [DetailModule::class])
@DetailScope
interface DetailComponent {

    fun inject(fragment: DetailFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build() : DetailComponent
    }
}