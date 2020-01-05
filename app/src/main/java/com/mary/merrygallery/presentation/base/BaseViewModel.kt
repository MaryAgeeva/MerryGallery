package com.mary.merrygallery.presentation.base

import androidx.lifecycle.ViewModel
import com.mary.mvi_core.MviState
import io.reactivex.subjects.BehaviorSubject

abstract class BaseViewModel<T : MviState> : ViewModel() {

    val state = BehaviorSubject.create<T>()
}