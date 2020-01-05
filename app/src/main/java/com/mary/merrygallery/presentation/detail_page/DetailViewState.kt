package com.mary.merrygallery.presentation.detail_page

import com.mary.merrygallery.presentation.detail_page.adapter.ImageUiModel
import com.mary.mvi_core.MviState

data class DetailViewState(val images: List<ImageUiModel> = listOf(),
                           val loading: Boolean = false,
                           val error: String? = null) : MviState