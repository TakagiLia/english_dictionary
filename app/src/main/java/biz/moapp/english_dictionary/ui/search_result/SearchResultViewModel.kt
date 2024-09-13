package biz.moapp.english_dictionary.ui.search_result

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import biz.moapp.english_dictionary.data.json_row.WordInfo
import biz.moapp.english_dictionary.utill.JsonUtil.convertJsonToWordInfo
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

    private val _searchHistory = MutableStateFlow<Map<String, WordInfo>>(emptyMap())
    val searchHistory: StateFlow<Map<String, WordInfo>> = _searchHistory.asStateFlow()

    var resultUiState by mutableStateOf(ResultUiState())
        private set

    fun getEnglishMean(word :String){
        viewModelScope.launch {
            /**ローディング**/
            resultUiState = resultUiState.copy(sendResultState = ResultUiState.SendResultState.Loading)

                /**過去に検索したものか**/
                val searchFlag = _searchHistory.value.containsKey(word)

                if (searchFlag) {
                    /**履歴から意味を取得**/
                    resultUiState = resultUiState.copy(
                        result = _searchHistory.value[word],
                        sendResultState = ResultUiState.SendResultState.Success(
                            _searchHistory.value[word]
                        )
                    )
                } else {
                    /**検索の通信処理**/
                    runCatching {
                        val result = withContext(ioDispatcher) {
                            retrofitOpenAiNetwork.responseCompletions(
                                retrofitOpenAiNetwork.createJson(
                                    word
                                )
                            )
                        }
                        /**処理の結果によってUI反映状態を変更**/
                        resultUiState = when (result) {
                            is ChatCompletions.Response.Success -> {
                                result.choices.map { value ->
                                    value.message?.content?.let {
                                        _jsonData.value = it
                                    }
                                }
                                Log.d("--wordInfo Json", "${result.choices[0].message?.content}")

                                /**受け取ったレスポンスをJsonに整形してWordInfoに変換**/
                                val wordInfo =
                                    convertJsonToWordInfo(result.choices[0].message?.content ?: "")
                                /**一度検索したものは履歴として残す**/
                                wordInfo?.let {
                                    _searchHistory.value = mutableMapOf(word to it)
                                }
                                Log.d("--wordInfo japaneseMeaning", "${wordInfo?.japaneseMeaning}")
                                Log.d("--wordInfo exampleSentences", "${wordInfo?.exampleSentences}")
                                Log.d("--wordInfo synonyms", "${wordInfo?.synonyms}")
                                Log.d("--wordInfo antonyms", "${wordInfo?.antonyms}")
                                Log.d("--wordInfo wordRoots", "${wordInfo?.wordRoots}")

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
                    }.onFailure { e ->
                        Log.e("--getEnglishMean Error", "Message:${e.message}", e)
                    }
            }
        }
    }
 }