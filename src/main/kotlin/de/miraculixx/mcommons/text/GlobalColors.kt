@file:Suppress("unused")

package de.miraculixx.mcommons.text

import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor

// Base colors - tag is used for MiniMessage
val cBase: TextColor = NamedTextColor.GRAY
const val cBaseTag = "<gray>"

// Variable colors, based on current theme
var cHighlight = "<blue>"
var cMark = "<#6e94ff>"

// Status colors
val cError: TextColor = NamedTextColor.RED
val cSuccess: TextColor = NamedTextColor.GREEN
