package com.mary.merrygallery.presentation.dirs_page

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes
import com.mary.domain.APP_TAG
import com.mary.merrygallery.MerryGalleryApp
import com.mary.merrygallery.R
import com.mary.merrygallery.presentation.base.BaseFragment
import com.mary.merrygallery.presentation.detail_page.DetailFragment
import com.mary.merrygallery.presentation.dirs_page.adapter.DirsAdapter
import com.mary.mvi_core.MviInitialView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.dirs_fragment.*
import javax.inject.Inject

class DirsFragment : BaseFragment<DirsIntent>(), MviInitialView<DirsIntent, DirsViewState, DirsIntent.GetData> {

    @Inject lateinit var store: DirsViewModel

    private var dirsAdapter = DirsAdapter()

    override fun getViewResource() = R.layout.dirs_fragment

    override fun injectDependencies() =
        MerryGalleryApp.appComponent
            .addDirs().build()
            .inject(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dirs_rv.apply {
            adapter = dirsAdapter
            layoutManager = GridLayoutManager(activity as Context,
                if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 4
                else 2)
        }
    }

    override fun onResume() {
        super.onResume()
        disposables.addAll(
            store.processIntents(observeIntents()).subscribe(),
            store.observeStates()
                .subscribe(::render) {
                    Log.e("$APP_TAG DirsScreen", "error: $it")
                })
    }

    override fun initialIntent(): Observable<DirsIntent.GetData> = Observable.just(DirsIntent.GetData)

    override fun observeIntents(): Observable<DirsIntent> =
        Observable.merge(initialIntent(), intents, dirsAdapter.clicksObservable)

    override fun render(state: DirsViewState) {
        dirs_refresh_layout.isRefreshing = false
        if(state.data.isNotEmpty())
            dirsAdapter.setList(state.data)
        else if(state.error != null)
            showSnack(dirs_root, getString(R.string.dirs_error_db))
        if(state.openDir != null)
            openDirectory(state.openDir)
    }

    override fun processInteractions() {
        dirs_refresh_layout.refreshes()
            .map { DirsIntent.GetData }
            .subscribe(intents)
    }

    private fun openDirectory(id: Int) {
        view?.let {
            Navigation.findNavController(it).navigate(R.id.detailFragment,
                Bundle().apply {
                    putInt(DetailFragment.DIR_ID, id)
                })
        }
    }

    companion object {
        fun newInstance() = DirsFragment()
    }
}
