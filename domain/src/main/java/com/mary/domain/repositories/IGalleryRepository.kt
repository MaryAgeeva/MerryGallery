package com.mary.domain.repositories

import com.mary.domain.entities.Directory
import com.mary.domain.entities.GalleryImage
import io.reactivex.Single

interface IGalleryRepository {
    
    fun getAllDirectories() : Single<List<Directory>>
    
    fun getImagesForDirectory(id: Int) : Single<List<GalleryImage>>
}