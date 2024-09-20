package biz.moapp.english_dictionary.ui.search_result.parts_compose.tab_content

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import biz.moapp.english_dictionary.R
import biz.moapp.english_dictionary.ui.search_result.parts_compose.SoundIcon

@Composable
fun TwoLinesListItem(word: String, japaneseMeaning: String,tts: TextToSpeech?){

    val onClick = remember(tts, word) {
        { tts?.speak(word, TextToSpeech.QUEUE_ADD, null, null) }
    }

    Spacer(modifier = Modifier.height(8.dp))
    Row(modifier = Modifier.fillMaxWidth().clickable(onClick = { onClick() },
        indication = null,
        interactionSource = remember { MutableInteractionSource() },),) {
        SoundIcon(word = word,tts = tts)
        Column(modifier = Modifier.padding(top = 2.dp)) {
            Text(text = stringResource(R.string.synonyms_eng_label) + word)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = stringResource(R.string.synonyms_jp_label) + japaneseMeaning)
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
    HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
    Spacer(modifier = Modifier.height(1.dp))
}