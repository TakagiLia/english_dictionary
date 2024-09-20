package biz.moapp.english_dictionary.ui.search_result.parts_compose

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SingleLineListItem(word:String, tts: TextToSpeech?){

    val onClick = remember(tts, word) {
        { tts?.speak(word, TextToSpeech.QUEUE_ADD, null, null) }
    }

    Spacer(modifier = Modifier.height(8.dp))
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable(
            onClick = { onClick() },
            indication = null,
            interactionSource = remember { MutableInteractionSource() },)
    ) {
        SoundIcon(word = word, tts = tts)
        Text(text = word)
    }
    Spacer(modifier = Modifier.height(8.dp))
    HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
    Spacer(modifier = Modifier.height(1.dp))
}