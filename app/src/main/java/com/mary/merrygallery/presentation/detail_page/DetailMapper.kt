package com.mary.merrygallery.presentation.detail_page

import com.mary.domain.entities.GalleryImage
import com.mary.merrygallery.di.scopes.DetailScope
import com.mary.merrygallery.presentation.detail_page.adapter.ImageUiModel
import javax.inject.Inject

@DetailScope
class DetailMapper @Inject constructor() {

    fun transform(images: List<GalleryImage>) : List<ImageUiModel> =
        images.map { ImageUiModel(it.title, it.path) }
}