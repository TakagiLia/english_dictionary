package biz.moapp.english_dictionary.ui.search_result

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import biz.moapp.english_dictionary.R
import biz.moapp.english_dictionary.navigation.Nav
import biz.moapp.english_dictionary.ui.search_result.parts_compose.tab_content.AntonymsTab
import biz.moapp.english_dictionary.ui.search_result.parts_compose.tab_content.ExampleTab
import biz.moapp.english_dictionary.ui.search_result.parts_compose.tab_content.MeanTab
import biz.moapp.english_dictionary.ui.search_result.parts_compose.tab_content.SynonymsTab
import biz.moapp.english_dictionary.ui.search_result.parts_compose.tab_content.WordRootsTab
import com.google.android.gms.ads.nativead.NativeAdView
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchResultScreen(modifier: Modifier = Modifier, keyWord :String? = "No KeyWord",
                       searchResultViewModel: SearchResultViewModel, navController:NavController,
                       nativeAdView: NativeAdView,){
    Log.d("--nativeAdView2", "SearchResultScreen:${nativeAdView.parent}")
    Column(modifier = modifier.fillMaxSize()) {
        /**タブ名前取得**/
        val tabLabels = stringArrayResource(R.array.tab_labels)

        /**タブインデックスの保持**/
        val viewPagerState = rememberPagerState(pageCount = { 5 })
        val viewPagerScope = rememberCoroutineScope()

        var initialized by remember { mutableStateOf(false) }
        val tts = rememberTextToSpeech()
        val backStackEntry by navController.currentBackStackEntryAsState()

        /**端末戻るボタンの制御**/
        BackHandler(
            enabled = true
        ) {
            navController.navigate(Nav.TopScreen.name) {
                backStackEntry?.destination?.route?.let {
                    popUpTo(it) { inclusive = true }
                }
            }
        }

        /**初期表示のための処理**/
        LaunchedEffect(Unit) {
            if (!initialized) {
                searchResultViewModel.getEnglishMean(keyWord ?: "No KeyWord")
                initialized = true
            }
        }

        /**タブ**/
//        SideEffect {
//            Log.d("--TabRow", "currentPage1: ${viewPagerState.currentPage}")
//        }
       TabRow(
            modifier = Modifier.fillMaxWidth(),
            selectedTabIndex = viewPagerState.currentPage,
        ) {
            tabLabels.forEachIndexed { index, value ->
//                SideEffect {
//                    Log.d("--TabRow", "index: ${index}")
//                    Log.d("--TabRow", "value: ${value}")
//                }
                Tab(
                    selected = viewPagerState.currentPage == index,
                    onClick = {
                        viewPagerScope.launch {
                            viewPagerState.animateScrollToPage(index)
                        }
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
        HorizontalPager(state = viewPagerState) { pageNum ->
                /**タブの内容**/
                when (searchResultViewModel.resultUiState.sendResultState) {
                    is ResultUiState.SendResultState.NotYet -> {}
                    is ResultUiState.SendResultState.Loading -> {
                        Column(
                            modifier = modifier.fillMaxSize().padding(bottom = 120.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is ResultUiState.SendResultState.Success -> {
                        (searchResultViewModel.resultUiState.sendResultState as ResultUiState.SendResultState.Success).results?.let { data ->
                            /**インデックスによってタブ内容が切り替わる**/
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Top,
                                horizontalAlignment = Alignment.Start
                            ) {
                                key(pageNum != viewPagerState.currentPage) {
                                    when (pageNum) {
                                        0 -> {
                                            MeanTab(
                                                modifier,
                                                keyWord ?: "No keyWord",
                                                data.japaneseMeaning, tts.value,
                                                nativeAdView
                                            )
                                        }

                                        1 -> {
                                            SynonymsTab(modifier, data.synonyms, tts.value)
                                        }

                                        2 -> {
                                            AntonymsTab(modifier, data.antonyms, tts.value)
                                        }

                                        3 -> {
                                            ExampleTab(modifier, data.exampleSentences, tts.value)
                                        }

                                        4 -> {
                                            WordRootsTab(data.wordRoots)
                                        }
                                    }
                                }
                            }
                        }
                    }

                    is ResultUiState.SendResultState.Error -> {
                        Column(
                            modifier = modifier.fillMaxSize().padding(bottom = 120.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = stringResource(R.string.search_result_error))
                        }
                    }
                }
        }
    }
}