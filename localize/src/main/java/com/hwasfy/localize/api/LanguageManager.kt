package com.hwasfy.localize.api

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext
import com.hwasfy.localize.util.LocaleHelper
import com.hwasfy.localize.util.SupportedLocales
import java.util.Locale

object LanguageManager {

    fun setLanguage(context: Context, appLocale: SupportedLocales) {
        val tag = appLocale.tag
        val locale = appLocale.locale

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Android 13+: just tell system LocaleManager
            context.getSystemService(android.app.LocaleManager::class.java)
                ?.applicationLocales = LocaleList.forLanguageTags(tag)
        } else {
            LocaleHelper.persist(context, tag)
            restartApp(context)
        }
        Locale.setDefault(locale)
    }

    fun getCurrentLocale(context: Context): SupportedLocales {
        val defaultTag = Locale.getDefault().toLanguageTag()

        val localeTag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val locales =
                context.getSystemService(android.app.LocaleManager::class.java).applicationLocales
            if (!locales.isEmpty) locales[0].toLanguageTag() else defaultTag
        } else {
            val locales = AppCompatDelegate.getApplicationLocales()
            if (!locales.isEmpty) locales[0]?.toLanguageTag() ?: LocaleHelper.getPersistedLocaleTag(
                context
            )
            else LocaleHelper.getPersistedLocaleTag(context)
        }
        return SupportedLocales.fromTag(localeTag)
    }

    private fun restartApp(context: Context) {
        val pm = context.packageManager
        val intent = pm.getLaunchIntentForPackage(context.packageName)?.apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        context.startActivity(intent)
        if (context is Activity) {
            context.finish()
        }
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
