# LocalizeMe

[![](https://jitpack.io/v/hassanwasfy/LocalizeMe.svg)](https://jitpack.io/#hassanwasfy/LocalizeMe)

**LocalizeMe** is a lightweight Android library for managing **app localization**.  
It allows you to easily **switch languages at runtime**, supports multiple locales, and works with XML-based and compose resources.  

## Contents
- [Features](#Features)
- [Supported Locales](#Supported-Locales)
- [Demo](#Demo)
- [Screenshots](#Screenshots)
- [Installation](#Installation)
- [Usage](#Usage)
- [Advantages](#Advantages)
- [Contributions](#Contributions)

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
    implementation("com.github.hassanwasfy:LocalizeMe:v1.0.8")
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

## Contributions

### Contributions are always welcome! If you'd like to contribute, please follow these steps:

- Fork the repository: Start by forking the `LocalizeMe` repository to your own GitHub account.
- Create a new branch: Create a new branch for your feature or bug fix. Use a descriptive name like `feature/add-new-locale` or `fix/crash-on-language-change`.
- Make your changes: Implement your changes and ensure the code follows the existing style and conventions.
- Create a pull request: Once your changes are ready, create a pull request (PR) from your forked repository to the main branch of the `original LocalizeMe repository`.
- Provide a clear description: In your PR, provide a detailed description of the changes you've made and the problem you're solving.

We appreciate your help in making this library even better!

