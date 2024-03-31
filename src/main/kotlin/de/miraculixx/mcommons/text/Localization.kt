@file:Suppress("unused")

package de.miraculixx.mcommons.text

import de.miraculixx.mcommons.serializer.YAMLSerializer
import de.miraculixx.mcommons.serializer.miniMessage
import java.io.File
import java.io.InputStream
import java.util.*

private var localization: MutableMap<String, YAMLSerializer> = mutableMapOf()
private var defaultLocale = "en_US"

/**
 * Get a translation for the given key. If no translation is found, the key will be returned in red. All inputs will be deserialized with miniMessages
 * @param key Localization Key
 * @param input Input variables. <input-i>
 */
fun msg(key: String, input: List<String> = emptyList(), locale: String = defaultLocale) = miniMessage.deserialize("<!i>" + (localization[locale]?.get<String>(key)?.replaceInput(input) ?: "<red>$key"))

/**
 * Get a translation for the given key. If no translation is found, the key will be returned instead.
 * @param key Localization Key
 * @param input Input variables. <input-i>
 */
fun msgString(key: String, input: List<String> = emptyList(), locale: String = defaultLocale) = localization[locale]?.get<String>(key)?.replaceInput(input) ?: key

/**
 * Get a translation for the given key. If no translation is found, the key will be returned in red.
 * @param key Localization Key
 * @param input Input variables. <input-i>
 * @param inline Inline string before every line (useful for listing)
 */
fun msgList(key: String, input: List<String> = emptyList(), inline: String = "<grey>   ", locale: String = defaultLocale) = msgString(key, input, locale).split("<br>").map {
    miniMessage.deserialize("$inline<!i>$it")
}.ifEmpty { listOf(cmp(inline + key, cError)) }

/**
 * Get the current used local if the proper syntax (e.g., en_US) is used.
 */
fun getLocal(): Locale {
    return try {
        Locale.forLanguageTag(defaultLocale) ?: Locale.ENGLISH
    } catch (_: Exception) {
        Locale.ENGLISH
    }
}

private fun String.replaceInput(input: List<String>): String {
    var msg = this
    input.forEachIndexed { index, s -> msg = msg.replace("<input-${index.plus(1)}>", s) }
    return msg
}

/**
 * Create your global localization instance. Creating multiple instances overrides each other and is not recommended.
 * @param folder The language folder that contains all language files
 * @param active The language that should be active from the start
 * @param keys All languages that should be loaded by default (all non-custom languages)
 * @see msg
 * @see msgList
 */
class Localization(private val folder: File, active: String, keys: List<Pair<String, InputStream?>>) {
    private val languages: MutableList<String> = mutableListOf()

    /**
     * All currently loaded language keys. Does not include custom language files that are added in runtime
     */
    fun getLoadedKeys(): List<String> {
        return languages
    }

    /**
     * Change the current language to a new one. The entered key must not be a loaded language, if the key cannot be found, it will rescan the language folder.
     * @param key The new language key
     * @return false if the entered language key cannot be found or point to an invalid config
     */
    fun setLanguage(key: String): Boolean {
        return if (localization[key] != null || checkFile(key)) {
            defaultLocale = key
            consoleAudience.sendMessage(prefix + cmp("Changed default language to ") + cmp(key, cHighlight))
            true
        } else false
    }

    /**
     * Reload all loaded language keys from the language folder
     */
    private fun checkFiles() {
        languages.clear()
        folder.listFiles()?.forEach {
            val key = it.nameWithoutExtension
            if (checkFile(key)) {
                languages.add(key)
                consoleAudience.sendMessage(prefix + cmp("Loaded language ") + cmp(key, cHighlight))
            }
        }
    }

    private fun checkFile(key: String): Boolean {
        val file = File("${folder.path}/$key.yml")
        if (!file.exists()) {
            consoleAudience.sendMessage(prefix + cmp("LANG - $key file not exist"))
            return false
        }
        val config = YAMLSerializer(file.inputStream(), key, file)
        if (config.get<String>("version") == null) {
            consoleAudience.sendMessage(prefix + cmp("LANG - $key file is not a valid language config"))
            return false
        }
        localization[key] = config
        return true
    }

    init {
        if (!folder.exists()) folder.mkdirs()
        keys.forEach {
            it.second?.readAllBytes()?.let { bytes -> File("${folder.path}/${it.first}.yml").writeBytes(bytes) }
        }
        checkFiles()
        setLanguage(active)
    }
}
