package com.mary.domain.use_cases.detail_page

import com.mary.domain.entities.GalleryImage
import io.reactivex.Single

interface IGetImagesUseCase {

    operator fun invoke(id: Int) : Single<List<GalleryImage>>
}