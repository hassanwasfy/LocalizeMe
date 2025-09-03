package com.hwasfy.localize.api

import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext
import androidx.core.os.LocaleListCompat
import com.hwasfy.localize.util.SupportedLocales
import java.util.Locale

object LanguageManager {

    fun setLanguage(context: Context, appLocale: SupportedLocales) {
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
        val defaultLanguage = Locale.getDefault().language
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val locales =
                context.getSystemService(android.app.LocaleManager::class.java).applicationLocales
            if (!locales.isEmpty) locales[0].language else defaultLanguage
        } else {
            val locales = AppCompatDelegate.getApplicationLocales()
            if (!locales.isEmpty) locales[0]?.language ?: defaultLanguage else defaultLanguage
        }
    }

    fun getCurrentLocale(context: Context): SupportedLocales {
        val defaultTag = Locale.getDefault().toLanguageTag()
        val localeTag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val locales =
                context.getSystemService(android.app.LocaleManager::class.java).applicationLocales
            if (!locales.isEmpty) locales[0].toLanguageTag() else defaultTag
        } else {
            val locales = AppCompatDelegate.getApplicationLocales()
            if (!locales.isEmpty) locales[0]?.toLanguageTag() ?: defaultTag else defaultTag
        }

        return SupportedLocales.fromTag(localeTag)
    }
}

@Composable
@ReadOnlyComposable
fun currentAppLocale(): SupportedLocales {
    val context = LocalContext.current
    return LanguageManager.getCurrentLocale(context)
}

@Composable
@ReadOnlyComposable
fun currentAppLanguageCode(): String {
    return currentAppLocale().locale.language
}

@Composable
@ReadOnlyComposable
fun currentResolvedLocale(): Locale {
    return currentAppLocale().locale
}