@file:Suppress("unused")

package de.miraculixx.mcommons.extensions

fun <K, V> List<K>.toMap(default: V): Map<K, V> {
    return buildMap {
        this@toMap.forEach { put(it, default) }
    }
}

/**
 * Returns the value after the given value in the list.
 * If the value is not found or the value is the last element, the first element is returned.
 */
fun <T> List<T>.nextValue(value: T): T {
    val index = indexOf(value)
    return if (index == -1 || index == size - 1) first() else this[index + 1]
}

/**
 * Returns the value after the given value in the array.
 * If the value is not found or the value is the last element, the first element is returned.
 */
fun <T> Array<T>.nextValue(value: T): T {
    val index = indexOf(value)
    return if (index == -1 || index == size - 1) first() else this[index + 1]
}
