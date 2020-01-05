package com.mary.merrygallery.presentation.detail_page.adapter

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageUiModel(val title: String,
                        val image: String?) : Parcelable