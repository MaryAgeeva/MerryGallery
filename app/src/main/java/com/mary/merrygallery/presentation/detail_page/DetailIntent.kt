package com.mary.merrygallery.presentation.detail_page

import com.mary.domain.entities.GalleryImage
import com.mary.mvi_core.MviIntent
import com.mary.mvi_core.MviInternalIntent

sealed class DetailIntent : MviIntent {

    data class GetData(val id: Int) : DetailIntent()

    data class ClickImage(val id: Int) : DetailIntent()


    object Loading : DetailIntent(), MviInternalIntent

    data class DataError(val error: String) : DetailIntent(), MviInternalIntent

    data class DataReceived(val images: List<GalleryImage>) : DetailIntent(), MviInternalIntent
}