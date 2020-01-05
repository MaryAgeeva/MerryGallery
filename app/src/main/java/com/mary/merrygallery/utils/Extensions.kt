package com.mary.merrygallery.utils

import io.reactivex.Observable

inline fun <reified T> Observable<*>.ofType() = ofType(T::class.java)