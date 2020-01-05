package com.mary.merrygallery.presentation.detail_page.adapter

import com.mary.merrygallery.presentation.base.BaseDiffUtilCallback

class DiffImagesCallback(newList: List<ImageUiModel>,
                         oldList: List<ImageUiModel>) : BaseDiffUtilCallback<ImageUiModel>(newList, oldList) {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].title == newList[newItemPosition].title

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].title == newList[newItemPosition].title &&
                oldList[oldItemPosition].image == newList[newItemPosition].image
}