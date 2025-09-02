# LocalizeMe

[![JitPack](https://img.shields.io/badge/jitpack-v1.0.0-blue)](https://jitpack.io/#HassanWasfy/LocalizeMe)

**LocalizeMe** is a lightweight Android library for managing **app localization**.  
It allows you to easily **switch languages at runtime**, supports multiple locales, and works with XML-based and compose resources.  

## Features

- Switch app language at runtime using `LanguageManager`.
- Supports **10+ languages** out of the box:
  
## Supported Locales

| Locale          | Tag    |
|-----------------|--------|
| English         | en-US  |
| Arabic (Egypt)  | ar-EG  |
| Urdu            | ur-PK  |
| Russian         | ru-RU  |
| French          | fr-FR  |
| Spanish         | es-ES  |
| German          | de-DE  |
| Italian         | it-IT  |
| Chinese         | zh-CN  |
| Japanese        | ja-JP  |


## Installation

### Step 1: Add JitPack repository

```gradle
repositories {
    maven { url 'https://jitpack.io' }
}
```

### Step 2: Add dependency

```gradle
dependencies {
    implementation ("com.github.HassanWasfy:LocalizeMe:1.0.0")
}
```

---

## Usage

```kotlin
import com.hwasfy.localize.api.LanguageManager
import com.hwasfy.localize.util.SupportedLocales

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(
                modifier = Modifier.fillMaxSize()
            ) { _ ->
                //Set app language
                Button(onClick = {
                    lifecycleScope.launch {
                        LanguageManager.setLanguage(this@MainActivity, SupportedLocales.AR_EG)
                    }
                }) {
                    Text("Select Language")
                }

                //Get app language
                val language: String = LanguageManager.getCurrentLanguage(this)

                //Get app locale
                val locale: Locale = LanguageManager.getCurrentLocale(this).locale
            }
        }
    }
}
```

---

## Advantages

- No complex setup required.
- Works with **XML-Compose resources**.
- Can be used in **any Android app**, just add dependency.
- Lightweight and modular, ready for Jitpack.

