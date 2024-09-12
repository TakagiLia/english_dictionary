package biz.moapp.english_dictionary.ui.top

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import biz.moapp.english_dictionary.utill.FileUtil.readCsvDataFromAssets
import biz.moapp.english_dictionary.data.Language
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.IOException
import java.text.Normalizer

class TopScreenViewModel : ViewModel() {

    private val _csvData = MutableStateFlow<List<Language>>(emptyList())
    val csvData: StateFlow<List<Language>> = _csvData.asStateFlow()

    private val _filterData = MutableStateFlow<List<Language>>(emptyList())
    val filterData: StateFlow<List<Language>> = _filterData.asStateFlow()

    private val _searchWord = MutableStateFlow<String>("")
    val searchWord: StateFlow<String> = _searchWord.asStateFlow()
    fun setSearchWord(word: String){
        _searchWord.value = word
    }

    /**AssetからCSV読み込み csvDataで保持**/
    fun readCsvDataFromAsset(context : Context){
        _csvData.value = runCatching { readCsvDataFromAssets(context) }
                        .getOrElse { e ->
                            if (e is IOException) {
                                Log.e("CSV Read Error", "Error reading CSV file", e)
                            } else {
                                Log.e("CSV Read Error", "An unexpected error occurred", e)
                            }
                            emptyList()
                        }
        initializeFilterList()
    }

    /**検索欄から文字を検索**/
    fun searchLanguage(word : String){
        /**全角半角を正規化　半角に調整**/
        val normalizedWord = Normalizer.normalize(word, Normalizer.Form.NFKC)

        /**該当する単語を抽出**/
        _filterData.value = if (normalizedWord.isBlank()) {
            _csvData.value
        } else {
            _csvData.value.filter { value ->
                value.englishMean.startsWith(normalizedWord)
            }
        }
    }

    /**フィルターリストの初期化**/
    fun initializeFilterList(){
        _filterData.value = _csvData.value
    }

    /**特定の値を初期化**/
    fun initializeValues() {
        _filterData.value = emptyList()
    }
}