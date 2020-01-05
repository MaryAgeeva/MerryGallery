package com.mary.domain.use_cases.dirs_page

import com.mary.domain.entities.Directory
import io.reactivex.Single

interface IGetDirectoriesUseCase {

    operator fun invoke() : Single<List<Directory>>
}