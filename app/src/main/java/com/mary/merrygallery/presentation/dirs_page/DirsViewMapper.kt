package com.mary.merrygallery.presentation.dirs_page

import com.mary.domain.entities.Directory
import com.mary.domain.utils.formatDateUI
import com.mary.merrygallery.di.scopes.DirsScope
import com.mary.merrygallery.presentation.dirs_page.adapter.DirsUiModel
import javax.inject.Inject

@DirsScope
class DirsViewMapper @Inject constructor() {

    fun transform(directories: List<Directory>) : List<DirsUiModel> =
        directories.map { DirsUiModel(it.id, it.title, it.cover, it.count) }
}