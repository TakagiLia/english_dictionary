package biz.moapp.english_dictionary.ui.top

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import biz.moapp.english_dictionary.navigation.Nav

@Composable
fun TopScreen(modifier: Modifier = Modifier,
              topScreenViewModel: TopScreenViewModel,
              navController: NavController) {

    val filterData = topScreenViewModel.filterData.collectAsState()

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
                Text(text = "該当する単語がありません")
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
                        navController.navigate("${Nav.SearchResultScreen.name}/${data.englishMean}")
                    }
                }
            }
        }
    }
}