package de.miraculixx.mcommons.text

import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.format.NamedTextColor

/**
 * Public console audience to log formatted messages everywhere
 */
lateinit var consoleAudience: Audience

/**
 * Internal component separator
 */
val _prefixSeparator = cmp(" >>", NamedTextColor.DARK_GRAY) + cmp(" ")

/**
 * Message prefix for all messages
 */
var prefix = cmp("MUtils", cHighlight) + _prefixSeparator
