package com.mary.merrygallery.presentation.dirs_page

import com.mary.domain.use_cases.dirs_page.IGetDirectoriesUseCase
import com.mary.merrygallery.di.scopes.DirsScope
import com.mary.merrygallery.utils.ofType
import com.mary.mvi_core.MviMiddleware
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@DirsScope
class DirsMiddleware @Inject constructor(private val getDirsAction: IGetDirectoriesUseCase)
                                                                        : MviMiddleware<DirsIntent, DirsViewState> {

    override fun bind(intents: Observable<DirsIntent>): Observable<DirsIntent> =
        Observable.merge<DirsIntent>(
            intents.ofType<DirsIntent.GetData>()
                .flatMap {
                    getDirsAction()
                        .subscribeOn(Schedulers.io())
                        .toObservable()
                }
                .map<DirsIntent> {
                    DirsIntent.DataReceived(it)
                }
                .startWith(DirsIntent.Loading)
                .onErrorReturn { DirsIntent.DataError(it.toString()) },

            intents.ofType<DirsIntent.DirectoryClick>()
        )
}