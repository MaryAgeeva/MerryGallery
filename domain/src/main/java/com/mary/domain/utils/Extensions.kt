package com.mary.domain.utils

import com.mary.domain.DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.*

fun String.parseDate() : Date =
    SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).parse(this)

fun Date.formatDateUI() : String =
    SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(this)