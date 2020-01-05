package com.mary.mvi_core

import io.reactivex.Observable

interface MviInitialView<I: MviIntent, S: MviState, In: MviIntent> : MviView<I, S> {

    fun initialIntent(): Observable<In>
}