package biz.moapp.english_dictionary.ui.search_result.parts_compose.tab_content

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import biz.moapp.english_dictionary.ui.search_result.parts_compose.ResultList

@Composable
fun ExampleTab(modifier: Modifier = Modifier, data: List<String>){
    ResultList(data)
}