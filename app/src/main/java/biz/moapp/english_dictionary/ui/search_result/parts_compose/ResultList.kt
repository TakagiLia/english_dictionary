package biz.moapp.english_dictionary.ui.search_result.parts_compose

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import biz.moapp.english_dictionary.R
import biz.moapp.english_dictionary.ui.search_result.rememberTextToSpeech

@Composable
fun ResultList(data: List<String>){
    val tts = rememberTextToSpeech()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        items(data) { value ->
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text(text = "・${value}")
                IconButton(onClick = {
                    tts.value?.speak(value, TextToSpeech.QUEUE_ADD,null, null)
                },
                    modifier = Modifier.padding(4.dp),) {
                    Icon(painter = painterResource(R.drawable.volume_up_24px),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
            Spacer(modifier = Modifier.height(1.dp))
        }

    }
}

