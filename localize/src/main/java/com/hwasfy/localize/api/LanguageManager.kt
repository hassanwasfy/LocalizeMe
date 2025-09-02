package com.hwasfy.localize.api

import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.hwasfy.localize.util.SupportedLocales

object LanguageManager {

    suspend fun setLanguage(context: Context, appLocale: SupportedLocales) {
        val tag = appLocale.tag

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.getSystemService(android.app.LocaleManager::class.java)
                .applicationLocales = LocaleList.forLanguageTags(tag)
        } else {
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(tag)
            )
        }
    }


    fun getCurrentLanguage(context: Context): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val locales = context.getSystemService(android.app.LocaleManager::class.java).applicationLocales
            if (!locales.isEmpty) locales[0].language else "en"
        } else {
            val locales = AppCompatDelegate.getApplicationLocales()
            if (!locales.isEmpty) locales[0]?.language ?: "en" else "en"
        }
    }

    fun getCurrentLocale(context: Context): SupportedLocales {
        val localeTag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val locales = context.getSystemService(android.app.LocaleManager::class.java).applicationLocales
            if (!locales.isEmpty) locales[0].toLanguageTag() else "en-US"
        } else {
            val locales = AppCompatDelegate.getApplicationLocales()
            if (!locales.isEmpty) locales[0]?.toLanguageTag() ?: "en-US" else "en-US"
        }

        return SupportedLocales.fromTag(localeTag)
    }
}
