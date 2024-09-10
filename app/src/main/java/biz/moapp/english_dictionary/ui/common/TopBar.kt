package biz.moapp.english_dictionary.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import biz.moapp.english_dictionary.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(){
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "")
        }
    )
}