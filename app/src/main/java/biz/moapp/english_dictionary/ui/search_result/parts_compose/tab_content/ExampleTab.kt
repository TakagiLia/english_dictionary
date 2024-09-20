package biz.moapp.english_dictionary.ui.search_result.parts_compose.tab_content

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import biz.moapp.english_dictionary.ui.search_result.parts_compose.SingleLineListItem

@Composable
fun ExampleTab(modifier: Modifier = Modifier, data: List<String>, tts: TextToSpeech?,){
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        items(data) { value ->
            SingleLineListItem(value, tts)
        }
    }
}