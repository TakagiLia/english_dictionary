package biz.moapp.english_dictionary.ui.search_result

import biz.moapp.english_dictionary.data.row.WordInfo

data class ResultUiState(
    val result: WordInfo? = null,
    val sendResultState: SendResultState = SendResultState.NotYet,
) {
    sealed interface SendResultState {
        object NotYet : SendResultState
        object Loading : SendResultState
        data class Success(val results: WordInfo?) : SendResultState
        data class Error(val message: String) : SendResultState
    }
}