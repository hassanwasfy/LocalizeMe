package com.hwasfy.localize.util

import android.content.Context
import com.hwasfy.localize.api.LanguageManager
import java.text.NumberFormat
import java.util.Locale

private fun Number.formatAsCurrency(
    context: Context,
    fractionDigits: Int = 2
): String {
    val locale: Locale = LanguageManager.getCurrentLocale(context).locale
    val formatter = NumberFormat.getCurrencyInstance(locale).apply {
        minimumFractionDigits = fractionDigits
        maximumFractionDigits = fractionDigits
    }
    return formatter.format(this)
}

fun Long.toMoneyString(context: Context, fractionDigits: Int = 2): String =
    this.formatAsCurrency(context, fractionDigits)

fun Double.toMoneyString(context: Context, fractionDigits: Int = 2): String =
    this.formatAsCurrency(context, fractionDigits)

fun Float.toMoneyString(context: Context, fractionDigits: Int = 2): String =
    this.toDouble().formatAsCurrency(context, fractionDigits)


fun String.toWesternDigits(): String {
    val arabicIndicDigits = listOf('٠', '١', '٢', '٣', '٤', '٥', '٦', '٧', '٨', '٩')
    val easternArabicDigits = listOf('۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸', '۹')

    return this.map { char ->
        when (char) {
            in arabicIndicDigits -> arabicIndicDigits.indexOf(char).toString()[0]
            in easternArabicDigits -> easternArabicDigits.indexOf(char).toString()[0]
            else -> char
        }
    }.joinToString("")
}

fun String.normalizeDigits(): String {
    val builder = StringBuilder()
    for (ch in this) {
        if (ch.isDigit()) {
            val digit = Character.getNumericValue(ch) // works for Arabic, Hindi, etc.
            builder.append(digit)
        } else {
            builder.append(ch)
        }
    }
    return builder.toString()
}
