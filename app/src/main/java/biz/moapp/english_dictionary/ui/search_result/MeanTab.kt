package biz.moapp.english_dictionary.ui.search_result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MeanTab(modifier: Modifier = Modifier, keyWord: String,data: List<String>){
    Column( modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start){
        /**英単語**/
        Row {
            Text(modifier = Modifier.padding(4.dp),
                text = keyWord,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp)
            /**ToDo音声アイコン入れる予定**/
        }
        /**意味**/
        Text(modifier = Modifier.padding(4.dp),
            text = "意味",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp)

        /**日本語意味のリスト**/
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(4.dp),
            horizontalAlignment = Alignment.Start
        ) {
            items(data) { value ->
                Text(text = "・${value}")
            }
        }
    }
}