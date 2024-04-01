@file:Suppress("unused")

package de.miraculixx.mcommons.extensions

import de.miraculixx.mcommons.serializer.jsonPretty
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import java.io.File

inline fun <reified T : Any> File.loadConfig(default: T): T {
    return if (exists()) {
        try {
            jsonPretty.decodeFromString<T>(readText())
        } catch (_: Exception) {
            saveConfig(default)
            default
        }
    } else {
        saveConfig(default)
        default
    }
}

inline fun <reified T : Any> File.saveConfig(config: T) {
    if (!exists() && parentFile != null) parentFile.mkdirs()
    writeText(jsonPretty.encodeToString(config))
}

fun File.createIfNotExists(): Boolean {
    return if (!exists()) {
        if (!parentFile.exists())
            parentFile.mkdirs()
        if (isDirectory)
            mkdir()
        else createNewFile()
    } else true
}
