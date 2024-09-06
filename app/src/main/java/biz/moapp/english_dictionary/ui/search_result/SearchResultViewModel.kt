package biz.moapp.english_dictionary.ui.search_result

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import biz.moapp.english_dictionary.JsonUtil.convertJsonToWordInfo
import biz.moapp.english_dictionary.network.RetrofitOpenAiNetwork
import biz.moapp.english_dictionary.network.model.ChatCompletions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel
    @Inject constructor(
    private val retrofitOpenAiNetwork: RetrofitOpenAiNetwork,
    private val ioDispatcher: CoroutineDispatcher,)
 : ViewModel()  {

    private val _jsonData = MutableStateFlow<String>("")
    val jsonData: StateFlow<String> = _jsonData.asStateFlow()

    var resultUiState by mutableStateOf(ResultUiState())
        private set

    fun getEnglishMean(word :String){
        viewModelScope.launch {
            /**ローディング**/
            resultUiState = resultUiState.copy(sendResultState = ResultUiState.SendResultState.Loading)

            /**通信処理**/
            runCatching {
                val result = withContext(ioDispatcher) {
                    retrofitOpenAiNetwork.responseCompletions( retrofitOpenAiNetwork.createJson(word))
                }

                resultUiState = when (result) {
                is ChatCompletions.Response.Success -> {
                    result.choices.map { value -> value.message?.content?.let{ _jsonData.value = it} }
                    /**受け取ったレスポンスをJsonに整形してWordInfoに変換**/
                    val wordInfo = convertJsonToWordInfo(result.choices[0].message?.content ?: "")
                    Log.d("--wordInfo japaneseMeaning","${wordInfo?.japaneseMeaning}")
                    Log.d("--wordInfo exampleSentences","${wordInfo?.exampleSentences}")
                    Log.d("--wordInfo synonyms","${wordInfo?.synonyms}")
                    Log.d("--wordInfo coOccurrences","${wordInfo?.coOccurrences}")

                    resultUiState.copy(
                        result = wordInfo,
                        sendResultState = ResultUiState.SendResultState.Success(
                            wordInfo
                        )
                    )
                }

                is ChatCompletions.Response.Failure -> {
                    resultUiState.copy(
                        sendResultState = ResultUiState.SendResultState.Error(
                            result.exception.message ?: "unknown error"
                        )
                    )
                }
            }
            }.onFailure {  e ->
                Log.e("--getEnglishMean Error","Message:${e.message}",e)
            }
        }
    }
 }