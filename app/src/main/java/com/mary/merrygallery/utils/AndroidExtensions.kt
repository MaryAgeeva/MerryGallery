package com.mary.merrygallery.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat

fun ViewGroup.inflateView(resource: Int): View = LayoutInflater.from(context).inflate(resource, this, false)

fun Context.color(resource: Int) = ContextCompat.getColor(this, resource)