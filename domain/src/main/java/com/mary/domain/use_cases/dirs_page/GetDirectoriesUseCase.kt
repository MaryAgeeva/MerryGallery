package com.mary.domain.use_cases.dirs_page

import com.mary.domain.entities.Directory
import com.mary.domain.repositories.IGalleryRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetDirectoriesUseCase @Inject constructor(private val repository: IGalleryRepository): IGetDirectoriesUseCase {

    override operator fun invoke(): Single<List<Directory>> = repository.getAllDirectories().subscribeOn(Schedulers.io())
}