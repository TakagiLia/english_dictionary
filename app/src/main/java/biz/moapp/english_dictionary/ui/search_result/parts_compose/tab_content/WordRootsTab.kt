package biz.moapp.english_dictionary.ui.search_result.parts_compose.tab_content

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WordRootsTab(text:String){
    Text(text = text, modifier = Modifier.padding(top = 16.dp))
}