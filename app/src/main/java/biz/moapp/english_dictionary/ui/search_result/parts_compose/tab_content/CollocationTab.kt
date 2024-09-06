package biz.moapp.english_dictionary.ui.search_result.parts_compose.tab_content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import biz.moapp.english_dictionary.ui.search_result.parts_compose.ResultList

@Composable
fun CollocationTab(modifier: Modifier = Modifier, data: List<String>){
    Column( modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start) {
        ResultList(data)
    }
}