package com.mary.mvi_core

import io.reactivex.Observable

interface MviView<I: MviIntent, S: MviState> {

    fun observeIntents() : Observable<I>

    fun render(state: S)
}