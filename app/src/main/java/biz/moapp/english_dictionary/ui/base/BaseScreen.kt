package biz.moapp.english_dictionary.ui.base

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import biz.moapp.english_dictionary.ui.common.BottomBar
import biz.moapp.english_dictionary.ui.common.TopBar
import biz.moapp.english_dictionary.ui.top.TopScreen

@Composable
fun BaseScreen() {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = { TopBar() }, bottomBar = { BottomBar()}){  innerPadding ->
        TopScreen(Modifier.padding(innerPadding))
    }
}