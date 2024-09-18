package biz.moapp.english_dictionary.ui.search_result.parts_compose.tab_content

import android.speech.tts.TextToSpeech
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import biz.moapp.english_dictionary.ui.search_result.parts_compose.ResultList

@Composable
fun ExampleTab(modifier: Modifier = Modifier, data: List<String>, tts: TextToSpeech?,){
    ResultList(data, tts)
}