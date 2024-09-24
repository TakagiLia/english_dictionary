package biz.moapp.english_dictionary.ui.search_result.parts_compose.tab_content

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import biz.moapp.english_dictionary.ui.search_result.parts_compose.NativeAds
import biz.moapp.english_dictionary.ui.search_result.parts_compose.SoundIcon
import com.google.android.gms.ads.nativead.NativeAdView

@Composable
fun MeanTab(modifier: Modifier = Modifier, keyWord: String, data: List<String>, tts: TextToSpeech?,
            nativeAdView: NativeAdView,){
    val onClick = remember(tts, keyWord) {
        { tts?.speak(keyWord, TextToSpeech.QUEUE_ADD, null, null) }
    }

    /**英単語**/
    Row(modifier = Modifier.clickable(onClick = { onClick() },
        indication = null,
        interactionSource = remember { MutableInteractionSource() },)) {
        Text(modifier = Modifier.padding(4.dp),
            text = keyWord,
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp)
        SoundIcon(word = keyWord, modifier = Modifier.padding(top = 8.dp), tts = tts)
    }
    /**意味**/
    Text(modifier = Modifier.padding(4.dp),
        text = "意味",
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp)

    /**日本語意味のリスト**/
    LazyColumn(
        modifier = Modifier.fillMaxHeight(0.4f),
        contentPadding = PaddingValues(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        items(data) { value ->
            Text(text = "・${value}")
        }
    }
    /**ネイティブ広告表示**/
    NativeAds(nativeAdView)
}