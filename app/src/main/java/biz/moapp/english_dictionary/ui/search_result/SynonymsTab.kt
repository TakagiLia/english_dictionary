package biz.moapp.english_dictionary.ui.search_result

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import biz.moapp.english_dictionary.DataMock.EnglishSynonym

@Composable
fun SynonymsTab(modifier: Modifier = Modifier, data: List<EnglishSynonym>){
    Column( modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start) {
        data.forEach { _ ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(4.dp),
                horizontalAlignment = Alignment.Start
            ) {
               items(data){
                   HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
                   Spacer(modifier = Modifier.height(8.dp))
                   Row {
                       Text(text = "英語：${it.eng}, ")
                       Text(text = "日本語：${it.jp}")
                   }
                   Spacer(modifier = Modifier.height(8.dp))
                   HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
                   Spacer(modifier = Modifier.height(1.dp))
               }
            }
        }
    }
}