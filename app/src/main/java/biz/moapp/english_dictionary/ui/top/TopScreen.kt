package biz.moapp.english_dictionary.ui.top

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import biz.moapp.english_dictionary.FileUtil.readCsvDataFromAssets
import java.io.IOException

@Composable
fun TopScreen(modifier: Modifier = Modifier) {

    val context = LocalContext.current

    /**AssetからCSV読み込み**/
    val csvData =  runCatching { readCsvDataFromAssets(context) }
        .getOrElse { e ->
            if (e is IOException) {
                Log.e("CSV Read Error", "Error reading CSV file", e)
            } else {
                Log.e("CSV Read Error", "An unexpected error occurred", e)
            }
            emptyList()
        }

    /**UI**/
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /**検索バー**/
        SearchBar(modifier)
        Spacer(modifier = Modifier.height(8.dp))

        /**単語表示**/
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(csvData) { data ->
                Text(text = data.englishMean)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}