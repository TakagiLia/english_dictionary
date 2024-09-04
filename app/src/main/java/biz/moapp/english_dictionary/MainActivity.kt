package biz.moapp.english_dictionary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
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
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BaseScreen(topScreenViewModel)
                }
            }
        }
    }
}