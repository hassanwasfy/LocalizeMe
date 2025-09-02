package com.hwasfy.localize.util

import java.util.Locale

enum class SupportedLocales(val tag: String, val locale: Locale) {
    EN_US("en-US", Locale("en", "US")),
    AR_EG("ar-EG", Locale("ar", "EG")),
    AR_SA("ar-SA", Locale("ar", "SA")),
    UR_PK("ur-PK", Locale("ur", "PK")),
    RU_RU("ru-RU", Locale("ru", "RU")),
    FR_FR("fr-FR", Locale("fr", "FR")),
    ES_ES("es-ES", Locale("es", "ES")),
    DE_DE("de-DE", Locale("de", "DE")),
    IT_IT("it-IT", Locale("it", "IT")),
    ZH_CN("zh-CN", Locale("zh", "CN")),
    JA_JP("ja-JP", Locale("ja", "JP"));

    companion object {
        fun fromTag(tag: String): SupportedLocales {
            return entries.find { it.tag.equals(tag, ignoreCase = true) }
                ?: throw IllegalArgumentException("Locale not supported: $tag")
        }
    }
}

