package com.mary.merrygallery.presentation.dirs_page

import com.mary.merrygallery.di.scopes.DirsScope
import com.mary.mvi_core.MviReducer
import javax.inject.Inject

@DirsScope
class DirsReducer @Inject constructor(private val mapper: DirsViewMapper) : MviReducer<DirsViewState, DirsIntent> {

    override fun reduce(state: DirsViewState, intent: DirsIntent): DirsViewState =
        when(intent) {
            is DirsIntent.Loading -> state.copy(loading = true, error = null, openDir = null)
            is DirsIntent.DataError -> state.copy(loading = false, error = intent.message, openDir = null)
            is DirsIntent.DataReceived -> state.copy(data = mapper.transform(intent.directories),
                                                    loading = false, error = null, openDir = null)
            is DirsIntent.DirectoryClick -> state.copy(openDir = intent.id)
            else -> state
        }
}