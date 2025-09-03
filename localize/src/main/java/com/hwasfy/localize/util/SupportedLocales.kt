package com.hwasfy.localize.util

import java.util.Locale

enum class SupportedLocales(val tag: String, val locale: Locale) {
    EN_US("en-US", Locale("en", "US")),
    AR_EG("ar-EG", Locale("ar", "EG")),
    UR_PK("ur-PK", Locale("ur", "PK")),
    RU_RU("ru-RU", Locale("ru", "RU")),
    FR_FR("fr-FR", Locale("fr", "FR")),
    ES_ES("es-ES", Locale("es", "ES")),
    DE_DE("de-DE", Locale("de", "DE")),
    IT_IT("it-IT", Locale("it", "IT")),
    ZH_CN("zh-CN", Locale("zh", "CN")),
    JA_JP("ja-JP", Locale("ja", "JP")),
    PT_BR("pt-BR", Locale("pt", "BR")), // Portuguese (Brazil)
    HI_IN("hi-IN", Locale("hi", "IN")), // Hindi (India)
    TR_TR("tr-TR", Locale("tr", "TR")), // Turkish
    FA_IR("fa-IR", Locale("fa", "IR")), // Persian (Iran)
    KO_KR("ko-KR", Locale("ko", "KR")), // Korean (South Korea)
    BN_BD("bn-BD", Locale("bn", "BD")), // Bengali (Bangladesh)
    ID_ID("id-ID", Locale("id", "ID")), // Indonesian
    MS_MY("ms-MY", Locale("ms", "MY")), // Malay
    TH_TH("th-TH", Locale("th", "TH")); // Thai


    companion object {
        fun fromTag(tag: String): SupportedLocales {
            return entries.find { it.tag.equals(tag, ignoreCase = true) }
                ?: throw IllegalArgumentException("Locale not supported: $tag")
        }
    }
}

