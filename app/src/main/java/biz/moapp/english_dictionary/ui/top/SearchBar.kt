package biz.moapp.english_dictionary.ui.top

import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchBar(modifier: Modifier = Modifier,
              topScreenViewModel: TopScreenViewModel){

    var text by remember { mutableStateOf("") }

    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top) {

        BasicTextField(
            value = text,
            onValueChange = { inputText ->
                text = inputText
                topScreenViewModel.searchLanguage(inputText)
            },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            visualTransformation = VisualTransformation.None,
            modifier = Modifier
                .border(1.dp, Color.Gray, RectangleShape)
                .padding(6.dp)
                .fillMaxWidth(0.7f),
            decorationBox = { innerTextField ->
                /**プレースホルダー**/
                Row(Modifier.padding(8.dp).horizontalScroll(rememberScrollState())) {
                    if (text.isEmpty()) {
                        Text(
                            "検索したい単語を入力",
                            color = Color.Gray,
                            fontSize = 20.sp
                        )
                    }
                    innerTextField()
                }
            },
            textStyle = TextStyle(fontSize = 20.sp),
        )
    }
}