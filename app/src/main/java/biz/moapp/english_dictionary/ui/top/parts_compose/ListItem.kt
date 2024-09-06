package biz.moapp.english_dictionary.ui.top.parts_compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import biz.moapp.english_dictionary.Language

@Composable
fun ListItem(data : Language, onClick: () -> Unit){
    Row(modifier = Modifier.padding(bottom = 0.5.dp)
        .fillMaxWidth()
        .clickable { onClick() },
    ){
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "${data.num}.")
            Spacer(modifier = Modifier.height(16.dp))
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = data.englishMean)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
    HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
}