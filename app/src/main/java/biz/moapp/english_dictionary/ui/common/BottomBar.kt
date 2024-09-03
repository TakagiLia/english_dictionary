package biz.moapp.english_dictionary.ui.common

import android.util.Log
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import biz.moapp.english_dictionary.R

@Composable
fun BottomBar(){

    NavigationBar() {
//        NAVIGATION_ITEMS.forEachIndexed { index, item ->
//            NavigationBarItem(
//                selected = item.screenName == selectedRoute,
//                onClick = {
//                    selectedItemIndex = index
//                    Log.d("--Navigation","onClick selectedItemIndex${selectedItemIndex}")
//                    when(selectedItemIndex){
//                        0 ->
//                            if(selectedRoute != Nav.MainScreen.name) navController.navigate(Nav.MainScreen.name){
//                                /**現在の画面までのバックスタックをクリアし、戻るボタンで前の画面に戻れないようにする
//                                 * popUpTo = 指定した画面までのバックスタック（遷移履歴）をクリア**/
//                                /**現在の画面までのバックスタックをクリアし、戻るボタンで前の画面に戻れないようにする
//                                 * popUpTo = 指定した画面までのバックスタック（遷移履歴）をクリア**/
//                                backStackEntry?.destination?.route?.let {
//                                    popUpTo(it) { inclusive = true }
//                                }
//                                /**同じ画面への重複遷移を防ぐ**/
//                                /**同じ画面への重複遷移を防ぐ**/
//                                launchSingleTop = true
//                            }
//                        1 -> if(selectedRoute != Nav.SummaryScreen.name) navController.navigate("${Nav.SummaryScreen.name}/bottomBar"){
//                            popUpTo(Nav.MainScreen.name) { inclusive = true }
//                            launchSingleTop = true
//                        }
//                        2 -> Log.d("--BottomBar","right")
//                    }
//                },
//                label = {
//                    Log.d("--Navigation","label index${index}")
//                    /**アイコンのラベル取得**/
//                    /**アイコンのラベル取得**/
//                    val title = when(index){
//                        0 -> stringResource(R.string.navigationbar_home)
//                        1 -> stringResource(R.string.navigationbar_summary)
//                        else -> {""}
//                    }
//                    Log.d("--Navigation","title${title}")
//                    Text(text = title)
//                },
//                alwaysShowLabel = false,
//                icon = {
//                    BadgedBox(
//                        badge = {
//                            if (item.badgeCount != null) {
//                                Badge {
//                                    Text(text = item.badgeCount.toString())
//                                }
//                            } else if (item.hasNews) {
//                                Badge()
//                            }
//                        }
//                    )
//                    {
//                        Icon(
//                            imageVector = if (index == selectedItemIndex) {
//                                item.selectedIcon
//                            } else item.unselectedIcon,
//                            contentDescription = ""
//                        )
//                    }
//                },
//            )
//        }
    }

}