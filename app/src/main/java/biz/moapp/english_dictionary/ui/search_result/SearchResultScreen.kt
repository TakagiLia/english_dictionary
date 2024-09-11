package biz.moapp.english_dictionary.ui.search_result

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import biz.moapp.english_dictionary.utill.JsonUtil.convertStringToList
import biz.moapp.english_dictionary.R
import biz.moapp.english_dictionary.ui.search_result.parts_compose.tab_content.CollocationTab
import biz.moapp.english_dictionary.ui.search_result.parts_compose.tab_content.ExampleTab
import biz.moapp.english_dictionary.ui.search_result.parts_compose.tab_content.MeanTab
import biz.moapp.english_dictionary.ui.search_result.parts_compose.tab_content.SynonymsTab

@Composable
fun SearchResultScreen(modifier: Modifier = Modifier,keyWord :String? = "No KeyWord", searchResultViewModel: SearchResultViewModel,){
    Column(modifier = modifier.fillMaxSize()) {
        /**タブ名前取得**/
        val tabLabels = stringArrayResource(R.array.tab_labels)
        /**タブインデックスの保持**/
        var selectedTabIndex by remember { mutableIntStateOf(0) }

        var initialized by remember { mutableStateOf(false) }

        /**端末戻るボタンの制御**/
        BackHandler(
            enabled = true
        ) {
            //ここに実行したい処理を記載(何もなけれ動作なしになる)
        }

        /**初期表示のための処理**/
        LaunchedEffect(Unit) {
            if (!initialized) {
                searchResultViewModel.getEnglishMean(keyWord ?: "No KeyWord")
                initialized = true
            }
        }

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

        /**タブの内容**/
        when(searchResultViewModel.resultUiState.sendResultState){
            is ResultUiState.SendResultState.NotYet -> {}
            is ResultUiState.SendResultState.Loading -> {
                Column(modifier = modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator()
                }
            }
            is ResultUiState.SendResultState.Success -> {
                (searchResultViewModel.resultUiState.sendResultState  as ResultUiState.SendResultState.Success).results?.let { data ->
                    /**インデックスによってタブ内容が切り替わる**/
                    when(selectedTabIndex){
                        0 -> { MeanTab(modifier,keyWord ?: "No keyWord", convertStringToList(data.japaneseMeaning),) }
                        1 -> { ExampleTab(modifier,data.exampleSentences) }
                        2 -> { SynonymsTab(modifier,data.synonyms) }
                        3 -> { CollocationTab(modifier,data.coOccurrences) }
                    }
                }
            }
            is ResultUiState.SendResultState.Error -> {
                Column(modifier = modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = stringResource(R.string.search_result_error))
                }
            }
        }
    }
}