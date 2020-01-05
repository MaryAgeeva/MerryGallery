package com.mary.merrygallery.presentation.dirs_page.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.view.clicks
import com.mary.merrygallery.R
import com.mary.merrygallery.presentation.base.BaseAdapter
import com.mary.merrygallery.presentation.base.BaseViewHolder
import com.mary.merrygallery.presentation.dirs_page.DirsIntent
import com.mary.merrygallery.presentation.utils.GlideApp
import com.mary.merrygallery.utils.inflateView
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_directory.view.*

class DirsAdapter : BaseAdapter<DirsAdapter.DirsViewHolder, DirsUiModel>() {

    val clicksObservable = PublishSubject.create<DirsIntent.DirectoryClick>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DirsViewHolder =
        DirsViewHolder(parent.inflateView(R.layout.item_directory))

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        clicksObservable.onComplete()
    }

    override fun setList(newList: List<DirsUiModel>) {
        val oldList = items
        val diffResult = DiffUtil.calculateDiff(DiffDirsCallback(newList, oldList))
        items = newList
        diffResult.dispatchUpdatesTo(this)
    }

    inner class DirsViewHolder(itemView: View) : BaseViewHolder<DirsUiModel>(itemView) {

        init {
            itemView.clicks()
                .map { DirsIntent.DirectoryClick(items[adapterPosition].id) }
                .subscribe(clicksObservable)
        }

        override fun initView(model: DirsUiModel) = with(itemView) {
            dirs_title.text = model.title
            dirs_count.text = model.count.toString()

            if(model.cover?.isNotEmpty() == true)
                GlideApp.with(itemView)
                    .load(model.cover)
                    .into(dirs_img)
        }
    }
}