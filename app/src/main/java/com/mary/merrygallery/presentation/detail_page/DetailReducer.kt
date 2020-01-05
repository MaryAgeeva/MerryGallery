package com.mary.merrygallery.presentation.detail_page

import com.mary.merrygallery.di.scopes.DetailScope
import com.mary.mvi_core.MviReducer
import javax.inject.Inject

@DetailScope
class DetailReducer @Inject constructor(private val mapper: DetailMapper) : MviReducer<DetailViewState, DetailIntent> {

    override fun reduce(state: DetailViewState, intent: DetailIntent): DetailViewState =
        when(intent) {
            is DetailIntent.DataReceived -> state.copy(images = mapper.transform(intent.images),
                                                        loading = false, error = null)
            is DetailIntent.DataError -> state.copy(error = intent.error)
            is DetailIntent.Loading -> state.copy(loading = true)
            else -> state
        }
}