package biz.moapp.english_dictionary.ui.top

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SearchBar(modifier: Modifier = Modifier){
    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top) {
        TextField(value = "入力", onValueChange = {})
        Button(onClick = { /*TODO*/ }) {
            Text(text = "検索")
        }
    }
}