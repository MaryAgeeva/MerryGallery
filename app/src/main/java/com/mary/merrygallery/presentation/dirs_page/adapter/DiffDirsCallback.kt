package com.mary.merrygallery.presentation.dirs_page.adapter

import com.mary.merrygallery.presentation.base.BaseDiffUtilCallback

class DiffDirsCallback(newList: List<DirsUiModel>,
                       oldList: List<DirsUiModel>) : BaseDiffUtilCallback<DirsUiModel>(newList, oldList) {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id &&
                oldList[oldItemPosition].title == newList[newItemPosition].title &&
                oldList[oldItemPosition].cover == newList[newItemPosition].cover
}