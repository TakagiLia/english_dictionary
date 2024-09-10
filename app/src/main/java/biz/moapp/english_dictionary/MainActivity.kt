package biz.moapp.english_dictionary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
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

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**Homeの戻るの制御**/
        onBackPressedDispatcher.addCallback(callback)

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
                    BaseScreen(topScreenViewModel, searchResultViewModel,)
                }
            }
        }
    }
    private val callback = object : OnBackPressedCallback(true) {
        /**handleOnBackPressedを呼び出して、戻るキーを押したときの処理を記述**/
        override fun handleOnBackPressed() {
            /**ハードの戻るボタンでアプリ終了**/
            return finish()
        }
    }
}