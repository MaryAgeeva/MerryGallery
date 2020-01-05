package com.mary.mvi_core

import io.reactivex.Observable

interface MviStore<I: MviIntent, S: MviState> {

    fun processIntents(intents: Observable<I>) : Observable<S>

    fun observeStates() : Observable<S>
}