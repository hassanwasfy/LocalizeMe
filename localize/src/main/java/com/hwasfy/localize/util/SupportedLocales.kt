package com.hwasfy.localize.util

import java.util.Locale

enum class SupportedLocales(val tag: String, val locale: Locale) {

    EN_US("en-US", Locale.forLanguageTag("en-US")),
    EN_UK("en-UK", Locale.forLanguageTag("en-UK")),
    EN_EG("en-EG", Locale.forLanguageTag("en-EG")),
    AR_EG("ar-EG", Locale.forLanguageTag("ar-EG")),
    UR_PK("ur-PK", Locale.forLanguageTag("ur-PK")),
    RU_RU("ru-RU", Locale.forLanguageTag("ru-RU")),
    FR_FR("fr-FR", Locale.forLanguageTag("fr-FR")),
    ES_ES("es-ES", Locale.forLanguageTag("es-ES")),
    DE_DE("de-DE", Locale.forLanguageTag("de-DE")),
    IT_IT("it-IT", Locale.forLanguageTag("it-IT")),
    ZH_CN("zh-CN", Locale.forLanguageTag("zh-CN")),
    JA_JP("ja-JP", Locale.forLanguageTag("ja-JP")),
    PT_BR("pt-BR", Locale.forLanguageTag("pt-BR")), // Portuguese (Brazil)
    HI_IN("hi-IN", Locale.forLanguageTag("hi-IN")), // Hindi (India)
    TR_TR("tr-TR", Locale.forLanguageTag("tr-TR")), // Turkish
    FA_IR("fa-IR", Locale.forLanguageTag("fa-IR")), // Persian (Iran)
    KO_KR("ko-KR", Locale.forLanguageTag("ko-KR")), // Korean (South Korea)
    BN_BD("bn-BD", Locale.forLanguageTag("bn-BD")), // Bengali (Bangladesh)
    ID_ID("id-ID", Locale.forLanguageTag("id-ID")), // Indonesian
    MS_MY("ms-MY", Locale.forLanguageTag("ms-MY")), // Malay
    TH_TH("th-TH", Locale.forLanguageTag("th-TH")), // Thai

    EN_SA("en-SA", Locale.forLanguageTag("en-SA")),      // Saudi Arabia
    EN_IQ("en-IQ", Locale.forLanguageTag("en-IQ")),      // Iraq
    EN_JO("en-JO", Locale.forLanguageTag("en-JO")),      // Jordan
    EN_MA("en-MA", Locale.forLanguageTag("en-MA")),      // Morocco
    EN_AE("en-AE", Locale.forLanguageTag("en-AE")),      // United Arab Emirates
    EN_LY("en-LY", Locale.forLanguageTag("en-LY")),      // Libya

    // New Arabic locales
    AR_SA("ar-SA", Locale.forLanguageTag("ar-SA")),      // Saudi Arabia
    AR_IQ("ar-IQ", Locale.forLanguageTag("ar-IQ")),      // Iraq
    AR_JO("ar-JO", Locale.forLanguageTag("ar-JO")),      // Jordan
    AR_MA("ar-MA", Locale.forLanguageTag("ar-MA")),      // Morocco
    AR_AE("ar-AE", Locale.forLanguageTag("ar-AE")),      // United Arab Emirates
    AR_LY("ar-LY", Locale.forLanguageTag("ar-LY"));      // Libya

    companion object {
        fun fromTag(tag: String): SupportedLocales {
            return entries.find { it.tag.equals(tag, ignoreCase = true) }
                ?: throw IllegalArgumentException("Locale not supported: $tag")
        }
    }
}

