package com.mary.merrygallery.presentation.detail_page.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mary.merrygallery.R
import com.mary.merrygallery.presentation.base.BaseAdapter
import com.mary.merrygallery.presentation.base.BaseViewHolder
import com.mary.merrygallery.presentation.utils.GlideApp
import com.mary.merrygallery.utils.inflateView
import kotlinx.android.synthetic.main.item_picture.view.*

class ImagesAdapter : BaseAdapter<ImagesAdapter.ImagesViewHolder, ImageUiModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder =
        ImagesViewHolder(parent.inflateView(R.layout.item_picture))

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {

    }

    override fun setList(newList: List<ImageUiModel>) {
        val oldList = items
        val diffResult = DiffUtil.calculateDiff(DiffImagesCallback(newList, oldList))
        items = newList
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ImagesViewHolder(itemView: View) : BaseViewHolder<ImageUiModel>(itemView) {

        override fun initView(model: ImageUiModel) = with(itemView) {
            if(model.image?.isNotEmpty() == true)
                GlideApp.with(itemView)
                    .load(model.image)
                    .into(pic_img)
        }
    }
}