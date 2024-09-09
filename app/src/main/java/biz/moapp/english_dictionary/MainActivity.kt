package biz.moapp.english_dictionary

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import biz.moapp.english_dictionary.ui.base.BaseScreen
import biz.moapp.english_dictionary.ui.search_result.SearchResultViewModel
import biz.moapp.english_dictionary.ui.theme.English_dictionaryTheme
import biz.moapp.english_dictionary.ui.top.TopScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MainActivity : ComponentActivity(),  TextToSpeech.OnInitListener {
    private var textToSpeech: TextToSpeech? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        textToSpeech = TextToSpeech(this, this)

        /**AssetからCSV読み込み**/
        val topScreenViewModel: TopScreenViewModel by viewModels()
        topScreenViewModel.readCsvDataFromAsset(this)

        val searchResultViewModel: SearchResultViewModel by viewModels()

        enableEdgeToEdge()
        setContent {
            English_dictionaryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BaseScreen(topScreenViewModel, searchResultViewModel, textToSpeech)
                }
            }
        }
    }

    override fun onInit(status: Int) {
        textToSpeech?.language = Locale.ENGLISH
    }
}