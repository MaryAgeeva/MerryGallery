package com.mary.merrygallery.presentation.base

import androidx.recyclerview.widget.DiffUtil

abstract class BaseDiffUtilCallback<T>(protected val newList: List<T>,
                                       protected val oldList: List<T>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size
}