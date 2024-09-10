package biz.moapp.english_dictionary.ui.search_result.parts_compose

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import biz.moapp.english_dictionary.R
import biz.moapp.english_dictionary.ui.search_result.rememberTextToSpeech

@Composable
fun SoundIcon(word :String){
    val tts = rememberTextToSpeech()
    IconButton(onClick = {
        tts.value?.speak(word, TextToSpeech.QUEUE_ADD,null, null)
    },
        modifier = Modifier.padding(4.dp),) {
        Icon(painter = painterResource(R.drawable.volume_up_24px),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary,
        )
    }
}