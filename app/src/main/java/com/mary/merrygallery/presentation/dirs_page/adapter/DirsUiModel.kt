package com.mary.merrygallery.presentation.dirs_page.adapter

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DirsUiModel(val id: Int,
                       val title: String,
                       val cover: String?,
                       val count: Int): Parcelable