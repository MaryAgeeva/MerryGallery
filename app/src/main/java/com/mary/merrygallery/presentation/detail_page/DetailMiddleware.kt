package com.mary.merrygallery.presentation.detail_page

import com.mary.domain.use_cases.detail_page.IGetImagesUseCase
import com.mary.merrygallery.di.scopes.DetailScope
import com.mary.merrygallery.utils.ofType
import com.mary.mvi_core.MviMiddleware
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@DetailScope
class DetailMiddleware @Inject constructor(private val getImagesAction: IGetImagesUseCase) : MviMiddleware<DetailIntent, DetailViewState> {

    override fun bind(intents: Observable<DetailIntent>): Observable<DetailIntent> =
        Observable.merge<DetailIntent>(
            intents.ofType<DetailIntent.GetData>()
                .flatMap {
                    getImagesAction(it.id)
                        .subscribeOn(Schedulers.io())
                        .toObservable()
                }
                .map<DetailIntent> { DetailIntent.DataReceived(it) }
                .onErrorReturn { DetailIntent.DataError(it.toString()) },

            intents.ofType<DetailIntent.ClickImage>()
        )
}