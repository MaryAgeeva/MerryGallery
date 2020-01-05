package com.mary.merrygallery.presentation.detail_page

import android.util.Log
import com.mary.domain.APP_TAG
import com.mary.merrygallery.di.scopes.DetailScope
import com.mary.mvi_core.MviStore
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

@DetailScope
class DetailViewModel @Inject constructor(private val middleware: DetailMiddleware,
                                          private val reducer: DetailReducer) : MviStore<DetailIntent, DetailViewState> {

    private val state = PublishSubject.create<DetailViewState>()

    override fun processIntents(intents: Observable<DetailIntent>): Observable<DetailViewState> =
        middleware.bind(intents)
            .doOnEach { Log.e("$APP_TAG ImgIntent", it.toString()) }
            .observeOn(AndroidSchedulers.mainThread())
            .scan(DetailViewState(), reducer::reduce)
            .doOnNext {
                Log.e("$APP_TAG ImgState", it.toString())
                state.onNext(it)
            }

    override fun observeStates(): Observable<DetailViewState> = state
}
