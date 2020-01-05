package com.mary.merrygallery.presentation.dirs_page

import com.mary.domain.entities.Directory
import com.mary.mvi_core.MviIntent
import com.mary.mvi_core.MviInternalIntent

sealed class DirsIntent : MviIntent {

    object GetData : DirsIntent()

    data class DirectoryClick(val id: Int) : DirsIntent()


    object Loading : DirsIntent(), MviInternalIntent

    data class DataReceived(val directories: List<Directory>) : DirsIntent(), MviInternalIntent

    data class DataError(val message: String) : DirsIntent(), MviInternalIntent
}