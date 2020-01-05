package com.mary.mvi_core

interface MviReducer<S: MviState, I: MviIntent> {

    fun reduce(state: S, intent: I) : S
}