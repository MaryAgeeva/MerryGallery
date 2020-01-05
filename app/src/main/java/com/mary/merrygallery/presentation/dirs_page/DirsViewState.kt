package com.mary.merrygallery.presentation.dirs_page

import com.mary.merrygallery.presentation.dirs_page.adapter.DirsUiModel
import com.mary.mvi_core.MviState

data class DirsViewState(val data: List<DirsUiModel> = listOf(),
                         val loading: Boolean = true,
                         val error: String? = null,
                         val openDir: Int? = null) : MviState