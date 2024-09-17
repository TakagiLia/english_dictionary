package biz.moapp.english_dictionary.ui.search_result.parts_compose

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import biz.moapp.english_dictionary.R

@Composable
fun SoundIcon(word :String, modifier: Modifier = Modifier, tts: TextToSpeech?){
    val onClick = remember(tts, word) {
        { tts?.speak(word, TextToSpeech.QUEUE_ADD, null, null) }
    }
    Row(modifier = modifier.clickable(onClick = { onClick() },
        indication = null, //indication = nullでリップル削除
        interactionSource = remember { MutableInteractionSource() },)){
        Icon(painter = painterResource(R.drawable.volume_up_24px),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(32.dp)
        )
    }
}