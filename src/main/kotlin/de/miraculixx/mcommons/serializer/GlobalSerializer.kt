@file:Suppress("unused")

package de.miraculixx.mcommons.serializer

import kotlinx.serialization.json.Json
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer

val miniMessage = MiniMessage.miniMessage()
val plainSerializer = PlainTextComponentSerializer.plainText()

val jsonPretty = Json {
    prettyPrint = true
    ignoreUnknownKeys = true
    encodeDefaults = true
}
