package de.miraculixx.mcommons.serializer

import kotlinx.serialization.json.Json
import net.kyori.adventure.text.minimessage.MiniMessage

val miniMessage = MiniMessage.miniMessage()

val jsonPretty = Json {
    prettyPrint = true
    ignoreUnknownKeys = true
    encodeDefaults = true
}
