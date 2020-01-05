package com.mary.merrygallery.di.components

import com.mary.merrygallery.di.modules.DirsModule
import com.mary.merrygallery.di.scopes.DirsScope
import com.mary.merrygallery.presentation.dirs_page.DirsFragment
import dagger.Subcomponent

@Subcomponent(modules = [DirsModule::class])
@DirsScope
interface DirsComponent {

    fun inject(fragment: DirsFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build() : DirsComponent
    }
}