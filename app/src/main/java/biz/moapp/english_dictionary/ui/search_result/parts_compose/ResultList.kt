package biz.moapp.english_dictionary.ui.search_result.parts_compose

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ResultList(data: List<String>, tts: TextToSpeech?){
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        items(data) { value ->
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                SoundIcon(word = value, tts = tts)
                Text(text = value)
            }
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
            Spacer(modifier = Modifier.height(1.dp))
        }

    }
}

