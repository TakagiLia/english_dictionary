package biz.moapp.english_dictionary.ui.top

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import biz.moapp.english_dictionary.FileUtil.readCsvDataFromAssets
import biz.moapp.english_dictionary.Language
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.IOException

class TopScreenViewModel : ViewModel() {
    private val _csvData = MutableStateFlow<List<Language>>(emptyList())
    val csvData: StateFlow<List<Language>> = _csvData.asStateFlow()

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
    }
}