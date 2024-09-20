package biz.moapp.english_dictionary.ui.top

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import biz.moapp.english_dictionary.R
import biz.moapp.english_dictionary.ui.top.parts_compose.ScrollBarList
import biz.moapp.english_dictionary.ui.top.parts_compose.SearchBar

@Composable
fun TopScreen(modifier: Modifier = Modifier,
              topScreenViewModel: TopScreenViewModel,
              navController: NavController) {

    val filterData = topScreenViewModel.filterData.collectAsState()

    /**端末戻るボタンの制御**/
    BackHandler(
        enabled = false
    ) {
        //ここに実行したい処理を記載(何もなけれ動作なしになる)
    }

    /**UI**/
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /**検索バー**/
        SearchBar(modifier, topScreenViewModel)

        if(filterData.value.isEmpty()){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(R.string.search_result_nothing))
            }
        }else {
            /**単語表示**/
            ScrollBarList(filterData.value, navController)
        }
    }
}