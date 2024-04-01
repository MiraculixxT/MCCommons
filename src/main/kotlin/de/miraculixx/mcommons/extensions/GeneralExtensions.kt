@file:Suppress("unused")

package de.miraculixx.mcommons.extensions

import java.util.*

fun String.toUUID(): UUID? {
    return try {
        UUID.fromString(this)
    } catch (_: IllegalArgumentException) {
        null
    }
}
