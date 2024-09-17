package biz.moapp.english_dictionary.ui.search_result.parts_compose.tab_content

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import biz.moapp.english_dictionary.data.json_row.Antonyms

@Composable
fun AntonymsTab(modifier: Modifier = Modifier, synonyms: List<Antonyms>,){
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(4.dp),
            horizontalAlignment = Alignment.Start
        ) {
                items(synonyms) { synonym, ->
                    TwoLinesListItem(synonym.word, synonym.japaneseMeaning,)
            }
        }
}