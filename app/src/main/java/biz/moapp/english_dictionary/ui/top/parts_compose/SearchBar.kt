package biz.moapp.english_dictionary.ui.top.parts_compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import biz.moapp.english_dictionary.R
import biz.moapp.english_dictionary.ui.top.TopScreenViewModel

@Composable
fun SearchBar(modifier: Modifier = Modifier,
              topScreenViewModel: TopScreenViewModel
){

    var text by remember { mutableStateOf("") }

    Row(modifier = Modifier.padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top){
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { inputText ->
                text = inputText
                topScreenViewModel.searchLanguage(inputText)
            },
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            visualTransformation = VisualTransformation.None,
            placeholder = {
                Row{
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "")
                    Text(stringResource(R.string.search_bar_placeholders),)
                }
            },
        )
    }

//    Row(modifier = Modifier.padding(horizontal = 8.dp)
//        .clip(RoundedCornerShape(8.dp))
//        .background(color = MaterialTheme.colorScheme.surfaceDim)
//        .border(1.dp,  MaterialTheme.colorScheme.outline, RoundedCornerShape(8.dp)),
//        horizontalArrangement = Arrangement.Center,
//        verticalAlignment = Alignment.Top) {
//        BasicTextField(
//            value = text,
//            onValueChange = { inputText ->
//                text = inputText
//                topScreenViewModel.searchLanguage(inputText)
//            },
//            maxLines = 1,
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
//            visualTransformation = VisualTransformation.None,
//            modifier = Modifier
//                .padding(6.dp)
//                .fillMaxWidth(),
//            decorationBox = { innerTextField ->
//                /**プレースホルダー**/
//                Row(
//                    Modifier
//                        .padding(8.dp)
//                        .horizontalScroll(rememberScrollState())) {
//                    if (text.isEmpty()) {
//                        Icon(imageVector = Icons.Filled.Search, contentDescription = "")
//                        Text(
//                            stringResource(R.string.search_bar_placeholders),
//                            color = Color.Gray,
//                            fontSize = 16.sp
//                        )
//                    }
//                    innerTextField()
//                }
//            },
//            textStyle = TextStyle(fontSize = 18.sp, color = Color.White),
//        )
//    }
}