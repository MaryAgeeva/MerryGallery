package com.mary.merrygallery.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import io.reactivex.Observable

inline fun <reified T> Observable<*>.ofType() = ofType(T::class.java)

inline fun <reified T: ViewModel> Fragment.getViewModel(
    providerFactory: ViewModelProvider.Factory
) : T {
    return ViewModelProviders.of(this, providerFactory)[T::class.java]
}