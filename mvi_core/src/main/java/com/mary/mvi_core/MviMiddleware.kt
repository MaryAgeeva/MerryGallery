package com.mary.mvi_core

import io.reactivex.Observable

interface MviMiddleware<I: MviIntent, S: MviState> {

    fun bind(intents: Observable<I>) : Observable<I>
}