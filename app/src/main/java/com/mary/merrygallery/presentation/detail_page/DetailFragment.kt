package com.mary.merrygallery.presentation.detail_page

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.mary.domain.APP_TAG
import com.mary.merrygallery.MerryGalleryApp
import com.mary.merrygallery.R
import com.mary.merrygallery.di.modules.DETAIL_VIEW_MODEL_NAME
import com.mary.merrygallery.presentation.base.BaseFragment
import com.mary.merrygallery.presentation.detail_page.adapter.ImagesAdapter
import com.mary.merrygallery.utils.getViewModel
import com.mary.mvi_core.MviInitialView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.detail_fragment.*
import javax.inject.Inject
import javax.inject.Named

class DetailFragment : BaseFragment<DetailIntent>(), MviInitialView<DetailIntent, DetailViewState, DetailIntent.GetData> {

    @Inject
    @Named(DETAIL_VIEW_MODEL_NAME)
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var store : DetailViewModel

    private val imgAdapter = ImagesAdapter()
    private val dirId by lazy { arguments?.getInt(DIR_ID)?: 0 }

    override fun getViewResource(): Int = R.layout.detail_fragment

    override fun injectDependencies() =
        MerryGalleryApp.appComponent
            .addDetail().build()
            .inject(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        store = getViewModel(viewModelFactory)
        images_rv.adapter = imgAdapter
    }

    override fun onResume() {
        super.onResume()
        disposables.addAll(
            store.processIntents(observeIntents()).subscribe(),
            store.observeStates()
                .subscribe(::render) {
                    Log.e("$APP_TAG ImgScreen", "error: $it")
                })
    }

    override fun initialIntent(): Observable<DetailIntent.GetData> {
        Log.d("$APP_TAG Detail", dirId.toString())
        return Observable.just(DetailIntent.GetData(dirId))
    }

    override fun observeIntents(): Observable<DetailIntent> = Observable.merge(initialIntent(), intents)

    override fun render(state: DetailViewState) {
        if(state.images.isNotEmpty())
            imgAdapter.setList(state.images)
    }

    override fun processInteractions() {

    }

    companion object {
        fun newInstance() = DetailFragment()

        const val DIR_ID = "DIR_ID"
    }
}
