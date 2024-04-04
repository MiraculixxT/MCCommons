@file:Suppress("unused")

package de.miraculixx.mcommons.extensions

import de.miraculixx.mcommons.text.msgString
import java.util.*

fun String.toUUID(): UUID? {
    return try {
        UUID.fromString(this)
    } catch (_: IllegalArgumentException) {
        null
    }
}

fun Boolean.msg(locale: Locale) = locale.msgString("common.${if (this) "boolTrue" else "boolFalse"}")
