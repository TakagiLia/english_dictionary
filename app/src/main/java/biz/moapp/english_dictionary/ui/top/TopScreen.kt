package biz.moapp.english_dictionary.ui.top

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import biz.moapp.english_dictionary.R
import biz.moapp.english_dictionary.navigation.Nav
import biz.moapp.english_dictionary.ui.top.parts_compose.ListItem
import biz.moapp.english_dictionary.ui.top.parts_compose.SearchBar

@Composable
fun TopScreen(modifier: Modifier = Modifier,
              topScreenViewModel: TopScreenViewModel,
              navController: NavController) {

    val filterData = topScreenViewModel.filterData.collectAsState()
    val backStackEntry by navController.currentBackStackEntryAsState()

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
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(filterData.value) { data ->
                    ListItem(data) {
                        navController.navigate("${Nav.SearchResultScreen.name}/${data.englishMean}"){
                            Log.d("--TopScreen", "${backStackEntry?.destination?.route}")
                            backStackEntry?.destination?.route?.let {
                                popUpTo(it) { inclusive = true }
                            }
                        }
                    }
                }
            }
        }
    }
}