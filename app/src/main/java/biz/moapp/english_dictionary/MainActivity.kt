package biz.moapp.english_dictionary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import biz.moapp.english_dictionary.ui.base.BaseScreen
import biz.moapp.english_dictionary.ui.theme.English_dictionaryTheme
import biz.moapp.english_dictionary.ui.top.TopScreenViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**AssetからCSV読み込み**/
        val topScreenViewModel: TopScreenViewModel by viewModels()
        topScreenViewModel.readCsvDataFromAsset(this)

        enableEdgeToEdge()
        setContent {
            English_dictionaryTheme {
                BaseScreen(topScreenViewModel)
            }
        }
    }
}