package biz.moapp.english_dictionary.ui.search_result

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SearchResultScreen(modifier: Modifier = Modifier,keyWord :String? = "No KeyWord"){
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = "SearchResultScreen:$keyWord")
    }
}