package biz.moapp.english_dictionary.ui.search_result.parts_compose

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import biz.moapp.english_dictionary.R

@Composable
fun SoundIcon(word :String, modifier: Modifier = Modifier, tts: TextToSpeech?){
        Icon(painter = painterResource(R.drawable.volume_up_24px),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(32.dp)
        )
}