package biz.moapp.english_dictionary.ui.search_result

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import biz.moapp.english_dictionary.DataMock.englishCoOccurrences
import biz.moapp.english_dictionary.DataMock.englishExampleSentences
import biz.moapp.english_dictionary.DataMock.englishSynonyms
import biz.moapp.english_dictionary.DataMock.japaneseMeanings
import biz.moapp.english_dictionary.R

@SuppressLint("ResourceType")
@Composable
fun SearchResultScreen(modifier: Modifier = Modifier,keyWord :String? = "No KeyWord"){
    Column(modifier = modifier.fillMaxSize()) {
        /**タブ名前取得**/
        val tabLabels = stringArrayResource(R.array.tab_labels)
        /**タブインデックスの保持**/
        var selectedTabIndex by remember { mutableIntStateOf(0) }

        /**タブ**/
        TabRow(
            selectedTabIndex = selectedTabIndex,
        ) {
            tabLabels.forEachIndexed { index,value ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                    },
                    text = {
                        Text(
                            text = value,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                )
            }
        }

        /**インデックスによってタブ内容が切り替わる**/
        when(selectedTabIndex){
            0 -> { MeanTab(modifier,keyWord ?: "No keyWord", japaneseMeanings) }
            1 -> { ExampleTab(modifier,englishExampleSentences) }
            2 -> { Synonyms(modifier,englishSynonyms) }
            3 -> { Collocation(modifier,englishCoOccurrences) }
        }
    }
}