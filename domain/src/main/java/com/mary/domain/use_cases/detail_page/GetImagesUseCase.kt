package com.mary.domain.use_cases.detail_page

import com.mary.domain.entities.GalleryImage
import com.mary.domain.repositories.IGalleryRepository
import io.reactivex.Single
import javax.inject.Inject

class GetImagesUseCase @Inject constructor(private val repository: IGalleryRepository) : IGetImagesUseCase {

    override operator fun invoke(id: Int): Single<List<GalleryImage>> = repository.getImagesForDirectory(id)
}