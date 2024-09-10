package biz.moapp.english_dictionary.ui.base

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import biz.moapp.english_dictionary.navigation.Nav
import biz.moapp.english_dictionary.ui.common.BottomBar
import biz.moapp.english_dictionary.ui.common.TopBar
import biz.moapp.english_dictionary.ui.search_result.SearchResultScreen
import biz.moapp.english_dictionary.ui.search_result.SearchResultViewModel
import biz.moapp.english_dictionary.ui.top.TopScreen
import biz.moapp.english_dictionary.ui.top.TopScreenViewModel

@Composable
fun BaseScreen(topScreenViewModel: TopScreenViewModel, searchResultViewModel: SearchResultViewModel,) {
    val navController = rememberNavController()
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = { TopBar(navController) }, bottomBar = { BottomBar()}){  innerPadding ->
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
                    navController)
            }
            composable(route = "${Nav.SearchResultScreen.name}/{keyWord}",
                arguments = listOf(navArgument("keyWord") { type = NavType.StringType })
            ) {backStackEntry ->
                topScreenViewModel.initializeValues()
                val keyWord = backStackEntry.arguments?.getString("keyWord")
                SearchResultScreen(Modifier.padding(innerPadding),keyWord, searchResultViewModel,)
            }
        }
    }
}