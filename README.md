# LocalizeMe

[![](https://jitpack.io/v/hassanwasfy/LocalizeMe.svg)](https://jitpack.io/#hassanwasfy/LocalizeMe)

**LocalizeMe** is a lightweight Android library for managing **app localization**.  
It allows you to easily **switch languages at runtime**, supports multiple locales, and works with
XML-based and compose resources.

## Contents
- [Features](#Features)
- [Supported Locales](#Supported-Locales)
- [Demo](#Demo)
- [Screenshots](#Screenshots)
- [Installation](#Installation)
- [Usage](#Usage)
- [Currency](#Currency)
- [Normalization](#Normalization)
- [Advantages](#Advantages)
- [Contributions](#Contributions)

## Features

- Switch app language at runtime using `LanguageManager`.
- Supports **33+ Locales** out of the box:

## Supported Locales

| Locale                   | Tag   | Locale                 | Tag   |
|--------------------------|-------|------------------------|-------|
| Arabic (Egypt)           | ar-EG | Urdu (Pakistan)        | ur-PK | 
| Arabic (Saudi Arabia)    | ar-SA | French (France)        | fr-FR |
| Arabic (Jordan)          | ar-JO | Spanish (Spain)        | es-ES |
| Arabic (Iraq)            | ar-IQ | Italian (Italy)        | it-IT |
| Arabic (Morocco)         | ar-MA | Japanese (Japan)       | ja-JP |
| Arabic (Libya)           | ar-LY | Hindi (India)          | hi-IN |
| Arabic (UAE)             | ar-AE | Persian (Iran)         | fa-IR |
| English (United States)  | en-US | Bengali (Bangladesh)   | bn-BD |
| English (Egypt)          | en-EG | Malay (Malaysia)       | ms-MY |
| English (United Kingdom) | en-UK | German (Germany)       | de-DE |
| English (Iraq)           | en-IQ | Chinese (China)        | zh-CN | 
| English (Morocco)        | en-MA | Portuguese (Brazil)    | pt-BR | 
| English (Saudi Arabia)   | en-SA | Turkish (Türkiye)      | tr-TR |
| English (Libya)          | en-LY | Korean (South Korea)   | ko-KR |
| English (Jordan)         | en-JO | Indonesian (Indonesia) | id-ID | 
| English (UAE)            | en-AE | Thai (Thailand)        | th-TH | 
| Russian (Russia)         | ru-RU |

## Demo

![Demo](videos/showcase.gif)

The demo shows how the app switches languages.

## Screenshots

| Arabic Example | English Example | Russian Example | China Example |
|------------------|----------------|------------------|------------------|
| ![Arabic](videos/arabic.png) | ![English](videos/english.png) | ![Russian](videos/russian.png) | ![China](videos/china.png) |

## Installation

### Step 1: Add JitPack repository

```gradle
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}
```

### Step 2: Add dependency

```gradle
dependencies {
    implementation("com.github.hassanwasfy:LocalizeMe:v${latest.version}")
}
```

---

## Usage

add in `res/values/string.xml (any qualifer you target and we support)`
then use in code Like:

### First apply in needed modules

```gradle
dependencies {
    implementation(project(":localize"))
}
```

### 1- Use from list of buttons

```kotlin
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.hwasfy.localize.api.LanguageManager
import com.hwasfy.localize.util.SupportedLocales
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val currentLocale = currentAppLocale()

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    TopAppBar(title = { Text("Language Demo") })
                }
            ) { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("Current: ${currentLocale.name} (${currentLocale.locale.displayName})")

                    Button(
                        onClick = {
                            LanguageManager.setLanguage(
                                this@MainActivity,
                                SupportedLocales.EN_US
                            )
                        }
                    ) { Text("Switch to English") }

                    Button(
                        onClick = {
                            LanguageManager.setLanguage(
                                this@MainActivity,
                                SupportedLocales.AR_EG
                            )
                        }
                    ) { Text("التبديل إلى العربية") }

                    Button(
                        onClick = {
                            LanguageManager.setLanguage(
                                this@MainActivity,
                                SupportedLocales.FR_FR
                            )
                        }
                    ) { Text("Passer en Français") }
                }
            }
        }
    }
}
```

### 2- Use from ViewModel/Repository

```kotlin
class SettingsRepository(private val context: Context) {

    fun getCurrentLanguageCode(): String {
        return LanguageManager.getCurrentLanguage(context) // "en", "ar", ...
    }

    fun getCurrentLocale(): SupportedLocales {
        return LanguageManager.getCurrentLocale(context) // e.g., SupportedLocales.AR_EG
    }
}
```

### 3- Use from Composable

```kotlin
@Composable
fun LanguageInfo() {
    val code = currentAppLanguageCode()
    val locale = currentResolvedLocale()

    Column {
        Text("Language Code: $code")
        Text("Locale: ${locale.displayName}")
    }
}
```

---

## Currency

### Currency Formatting & Digit Utilities

Extension helpers to turn `Long`, `Double`, or `Float` values into a fully-localized currency string
based on the app’s current locale.

```kotlin
fun Long.toMoneyString(context: Context, fractionDigits: Int = 2): String =
    this.formatAsCurrency(context, fractionDigits)

fun Double.toMoneyString(context: Context, fractionDigits: Int = 2): String =
    this.formatAsCurrency(context, fractionDigits)

fun Float.toMoneyString(context: Context, fractionDigits: Int = 2): String =
    this.toDouble().formatAsCurrency(context, fractionDigits)
```

#### example

```kotlin
val amount: Long = 500
textView.text = amount.toMoneyString(context, fractionDigits = 2)   //XML "EGP 500,00" or "$500.00"
Text(
    amount.toMoneyString(
        LocalContext.current,
        fractionDigits = 2
    )
) //Compose text with money formatted
```

## Normalization

Helpers for converting Arabic-Indic or Eastern-Arabic digits to western ASCII digits.

```kotlin
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
```

#### example

```kotlin
val mixed = "سعر ١٢٣٤"
val western = mixed.toWesternDigits()     // "سعر 1234"
val normalized = mixed.normalizeDigits()  // "سعر 1234"
```

## Advantages

- No complex setup required.
- Works with **XML-Compose resources**.
- Can be used in **any Android app**, just add dependency.
- Lightweight and modular, ready for Jitpack.

## Contributions

### Contributions are always welcome! If you'd like to contribute, please follow these steps:

- Fork the repository: Start by forking the `LocalizeMe` repository to your own GitHub account.
- Create a new branch: Create a new branch for your feature or bug fix. Use a descriptive name like `feature/add-new-locale` or `fix/crash-on-language-change`.
- Make your changes: Implement your changes and ensure the code follows the existing style and conventions.
- Create a pull request: Once your changes are ready, create a pull request (PR) from your forked repository to the main branch of the `original LocalizeMe repository`.
- Provide a clear description: In your PR, provide a detailed description of the changes you've made and the problem you're solving.

We appreciate your help in making this library even better!

