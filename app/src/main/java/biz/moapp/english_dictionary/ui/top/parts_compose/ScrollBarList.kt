package biz.moapp.english_dictionary.ui.top.parts_compose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import biz.moapp.english_dictionary.data.Language
import biz.moapp.english_dictionary.navigation.Nav
import com.google.android.gms.ads.AdView

@Composable
fun ScrollBarList(filterData: List<Language>,
                  navController: NavController,
                  banner: AdView
){

    /**LazyColumn の状態を管理**/
    val listState = rememberLazyListState()
    val backStackEntry by navController.currentBackStackEntryAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(end = 8.dp),
            state = listState, // 状態を LazyColumn に渡す
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(filterData) { data ->
                ListItem(data) {
                    navController.navigate("${Nav.SearchResultScreen.name}/${data.englishMean}") {
                        backStackEntry?.destination?.route?.let {
                            popUpTo(it) { inclusive = true }
                        }
                    }
                }
            }
        }
        /**リスト全件表示の場合、スクロールバー表示（リストの数が最後のアイテムのNumと同じ）**/
        if(filterData.size == filterData.last().num.toInt()) {
            ScrollBar(
                listState = listState,
            )
        }
        /**バナー広告**/
        BannerAds(banner)
    }
}

@Composable
fun BoxScope.ScrollBar(
    modifier: Modifier = Modifier,
    listState: LazyListState,
) {
    val scrollBarWidth = 8.dp
    val color = MaterialTheme.colorScheme.primary

        Canvas(
            modifier = modifier
                .align(Alignment.CenterEnd)
                .fillMaxSize()
        ) {
            val viewHeight = size.height
            val totalCount = listState.layoutInfo.totalItemsCount

            if (totalCount == 0) return@Canvas

            val firstVisibleItemIndex = listState.firstVisibleItemIndex
            val firstVisibleItemScrollOffset = listState.firstVisibleItemScrollOffset
            val visibleItemCount = listState.layoutInfo.visibleItemsInfo.size

            val scrollRatio = firstVisibleItemIndex.toFloat() / totalCount

            /**スクロールバーの位置とサイズを計算**/
            val scrollbarHeight = viewHeight * (visibleItemCount.toFloat() / totalCount)
            val scrollbarTopY1 = scrollRatio * viewHeight

            /**次のアイテムの位置とサイズを計算**/
            val scrollRatio2 = (firstVisibleItemIndex + 1).toFloat() / totalCount
            val scrollbarTopY2 = scrollRatio2 * viewHeight

            /**表示中の先頭アイテムの高さ**/
            val firstVisibleItemHeight = listState.layoutInfo.visibleItemsInfo.getOrNull(0)?.size

            /**スクロールバー位置の微調整(スクロール量をスクロールバーのoffsetに変換する。offsetの範囲はこのアイテムと次のアイテムのスクロールバーの位置)**/
            val scrollbarTopOffset = if (firstVisibleItemHeight == null || firstVisibleItemHeight == 0) {
                /**先頭アイテムの高さが不明なので微調整なし**/
                0f
            } else {
                firstVisibleItemScrollOffset.toFloat() / firstVisibleItemHeight * (scrollbarTopY2 - scrollbarTopY1)
            }
            drawRoundRect(
                color = color,
                cornerRadius = CornerRadius(8f),
                topLeft = Offset(size.width - scrollBarWidth.toPx(), scrollbarTopY1 + scrollbarTopOffset),
                size = Size(scrollBarWidth.toPx(), scrollbarHeight)
            )
        }
}