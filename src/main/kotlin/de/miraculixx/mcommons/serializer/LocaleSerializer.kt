@file:Suppress("unused")

package de.miraculixx.mcommons.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.*

object LocaleSerializer : KSerializer<Locale> {
    override val descriptor = PrimitiveSerialDescriptor("Locale", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Locale {
        return Locale.forLanguageTag(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: Locale) {
        encoder.encodeString(value.toString())
    }
}
