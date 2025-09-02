package com.hwasfy.localizeme

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.hwasfy.localize.api.LanguageManager
import com.hwasfy.localize.util.SupportedLocales
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(
                modifier = Modifier.fillMaxSize()
            ) { p ->
                LanguageSwitcherScreen(modifier = Modifier.padding(p)) { selected ->
                    lifecycleScope.launch {
                        LanguageManager.setLanguage(this@MainActivity, selected)
                    }
                }
            }
        }
    }
}

@Composable
fun LanguageSwitcherScreen(modifier: Modifier, onLanguageSelected: (SupportedLocales) -> Unit) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 4.dp)
    ) {
        item {
            Text(stringResource(R.string.text))
            Spacer(modifier = Modifier.height(16.dp))
            Text(stringResource(R.string.numbers))
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            BBB(onClick = { onLanguageSelected(SupportedLocales.EN_US) }) {
                TTT(stringResource(R.string.switch_to_en),R.drawable.flag_us)
            }
        }
        item {
            BBB(onClick = { onLanguageSelected(SupportedLocales.AR_EG) }) {
                TTT(stringResource(R.string.switch_to_ar),R.drawable.flag_eg)
            }
        }
        item {
            BBB(onClick = { onLanguageSelected(SupportedLocales.UR_PK) }) {
                TTT(stringResource(R.string.switch_to_ur),R.drawable.flag_ur)
            }
        }
        item {
            BBB(onClick = { onLanguageSelected(SupportedLocales.RU_RU) }) {
                TTT(stringResource(R.string.switch_to_ru),R.drawable.flag_ru)
            }
        }
        item {
            BBB(onClick = { onLanguageSelected(SupportedLocales.FR_FR) }) {
                TTT(stringResource(R.string.switch_to_fr),R.drawable.flag_fr)
            }
        }
        item {
            BBB(onClick = { onLanguageSelected(SupportedLocales.ES_ES) }) {
                TTT(stringResource(R.string.switch_to_es),R.drawable.flag_es)
            }
        }
        item {
            BBB(onClick = { onLanguageSelected(SupportedLocales.DE_DE) }) {
                TTT(stringResource(R.string.switch_to_de),R.drawable.flag_de)
            }
        }
        item {
            BBB(onClick = { onLanguageSelected(SupportedLocales.IT_IT) }) {
                TTT(stringResource(R.string.switch_to_it),R.drawable.flag_it)
            }
        }
        item {
            BBB(onClick = { onLanguageSelected(SupportedLocales.ZH_CN) }) {
                TTT(stringResource(R.string.switch_to_zh),R.drawable.flag_zh)
            }
        }
        item {
            BBB(onClick = { onLanguageSelected(SupportedLocales.JA_JP) }) {
                TTT(stringResource(R.string.switch_to_ja),R.drawable.flag_ja)
            }
        }
    }
}

@Composable
fun BBB(onClick: () -> Unit, content: @Composable () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        content()
    }
}

@Composable
fun TTT(title: String, @DrawableRes icon: Int){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(title, modifier = Modifier.padding(vertical = 8.dp), fontSize = 14.sp)
    }
}
