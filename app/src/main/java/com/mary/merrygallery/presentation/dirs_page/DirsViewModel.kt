package com.mary.merrygallery.presentation.dirs_page

import android.util.Log
import com.mary.domain.APP_TAG
import com.mary.merrygallery.di.scopes.DirsScope
import com.mary.mvi_core.MviStore
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

@DirsScope
class DirsViewModel @Inject constructor(private val middleware: DirsMiddleware,
                                        private val reducer: DirsReducer) : MviStore<DirsIntent, DirsViewState> {

    private val state = PublishSubject.create<DirsViewState>()

    override fun processIntents(intents: Observable<DirsIntent>): Observable<DirsViewState> =
        middleware.bind(intents)
            .doOnEach { Log.e("$APP_TAG DirsIntent", it.toString()) }
            .observeOn(AndroidSchedulers.mainThread())
            .scan(DirsViewState(), reducer::reduce)
            .doOnNext {
                Log.e("$APP_TAG DirsState", it.toString())
                state.onNext(it)
            }

    override fun observeStates(): Observable<DirsViewState> = state
}
