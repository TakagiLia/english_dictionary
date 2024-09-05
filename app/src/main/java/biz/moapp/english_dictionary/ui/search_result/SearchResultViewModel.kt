package biz.moapp.english_dictionary.ui.search_result

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun getEnglishMean(word :String){
        viewModelScope.launch {

            runCatching {
                val result = withContext(ioDispatcher) {
                    retrofitOpenAiNetwork.responseCompletions( retrofitOpenAiNetwork.createJson(word))
                }

                when (result) {
                    is ChatCompletions.Response.Success -> {
                        result.choices.map { value -> value.message?.content?.let{ _jsonData.value = it} }
                        Log.d("--result response１-","${result.choices.map { it.message?.content }}")
                    }

                    is ChatCompletions.Response.Failure -> {

                    }
                }
            }.onFailure {  e ->
                Log.e("--summary Error","Message:${e.message}",e)
            }
        }
    }
 }