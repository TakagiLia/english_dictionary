package biz.moapp.english_dictionary.ui.search_result.parts_compose.tab_content

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import biz.moapp.english_dictionary.ui.search_result.parts_compose.SoundIcon
import biz.moapp.english_dictionary.ui.search_result.rememberTextToSpeech

@Composable
fun MeanTab(modifier: Modifier = Modifier, keyWord: String, data: List<String>,){
    val tts = rememberTextToSpeech()
    /**英単語**/
    Row {
        Text(modifier = Modifier.padding(4.dp),
            text = keyWord,
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp)
        SoundIcon(word = keyWord, modifier = Modifier.padding(top = 8.dp), tts = tts.value)
    }
    /**意味**/
    Text(modifier = Modifier.padding(4.dp),
        text = "意味",
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp)

    /**日本語意味のリスト**/
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        items(data) { value ->
            Text(text = "・${value}")
        }
    }
}