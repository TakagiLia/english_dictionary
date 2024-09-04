package biz.moapp.english_dictionary.ui.top

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import biz.moapp.english_dictionary.Language
import biz.moapp.english_dictionary.navigation.Nav

@Composable
fun ListItem(data : Language, onClick: () -> Unit){
    Row(modifier = Modifier.padding(bottom = 0.5.dp)
//        .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(4.dp))
        .fillMaxWidth()
        .clickable { onClick() },
    ){
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "${data.num}.")
            Spacer(modifier = Modifier.height(4.dp))
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = data.englishMean)
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
    HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
}