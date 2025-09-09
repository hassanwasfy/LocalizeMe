package com.hwasfy.localize.util

import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.core.content.edit
import java.util.Locale

object LocaleHelper {
    private const val PREFS = "language"
    private const val KEY = "languageToLoad"

    fun persist(context: Context, tag: String) {
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .edit { putString(KEY, tag) }
    }

    fun getPersistedLocaleTag(context: Context): String {
        return context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .getString(KEY, Locale.getDefault().toLanguageTag()) ?: "en-US"
    }

    fun wrapContext(base: Context): Context {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return base
        }
        val tag = getPersistedLocaleTag(base)
        val locale = Locale.forLanguageTag(tag)
        val config = base.resources.configuration.apply {
            setLocale(locale)
            setLocales(LocaleList(locale))
        }
        return base.createConfigurationContext(config)
    }
}



