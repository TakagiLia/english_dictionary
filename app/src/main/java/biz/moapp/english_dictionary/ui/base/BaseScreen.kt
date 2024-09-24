package biz.moapp.english_dictionary.ui.base

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import biz.moapp.english_dictionary.navigation.Nav
import biz.moapp.english_dictionary.ui.common.TopBar
import biz.moapp.english_dictionary.ui.search_result.SearchResultScreen
import biz.moapp.english_dictionary.ui.search_result.SearchResultViewModel
import biz.moapp.english_dictionary.ui.top.TopScreen
import biz.moapp.english_dictionary.ui.top.TopScreenViewModel
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.nativead.NativeAdView

@Composable
fun BaseScreen(topScreenViewModel: TopScreenViewModel, searchResultViewModel: SearchResultViewModel,
               banner: AdView, nativeAdView: NativeAdView,) {
    val navController = rememberNavController()
    val searchWord = searchResultViewModel.searchKeyWord.collectAsState()
    val nativeAd = remember { nativeAdView }
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = { TopBar(navController, searchWord.value) }, /*bottomBar = { BottomBar()}*/){  innerPadding ->
        NavHost(
            navController = navController, startDestination = Nav.TopScreen.name,
            enterTransition = {
                EnterTransition.None
            },
            popEnterTransition = {
                EnterTransition.None
            },
            exitTransition = {
                ExitTransition.None
            },
            popExitTransition = {
                ExitTransition.None
            },
        ) {
            composable(route = Nav.TopScreen.name,) {
                TopScreen(Modifier.padding(innerPadding),
                    topScreenViewModel,
                    navController, banner)
            }
            composable(route = "${Nav.SearchResultScreen.name}/{keyWord}",
                arguments = listOf(navArgument("keyWord") { type = NavType.StringType })
            ) {backStackEntry ->
                val keyWord = backStackEntry.arguments?.getString("keyWord")
                SearchResultScreen(Modifier.padding(innerPadding), keyWord,
                    searchResultViewModel, navController, nativeAd)
            }
        }
    }
}