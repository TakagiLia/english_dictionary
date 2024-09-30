package biz.moapp.english_dictionary.ui.search_result.parts_compose.tab_content

import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import biz.moapp.english_dictionary.utill.JsonUtil.replaceEnglishWordsWithNumbers

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WordRootsTab(text:String,  tts: TextToSpeech?){

    /**文字列文章を英単語ごとにリスト化**/
    val sentenceList = replaceEnglishWordsWithNumbers(text)
    Log.d("--check1","$sentenceList")

    /**UI**/
    FlowRow(maxItemsInEachRow = 6) {
        sentenceList.forEach { sentence ->
            Log.d("--check2", sentence)
            val onClick = remember(tts, sentence) {
                    { tts?.speak(sentence, TextToSpeech.QUEUE_ADD, null, null) }
            }

            /**「」で囲まれているのは読み上げ対象のため音声再生アクションの追加**/
            val clickModifier = if(sentence.contains(Regex("[a-zA-Z]"))){
                Modifier
                    .clickable(
                        onClick = { onClick() },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },)
            } else {
                Modifier
            }

            Text(text = sentence,modifier = clickModifier)
        }
    }
}