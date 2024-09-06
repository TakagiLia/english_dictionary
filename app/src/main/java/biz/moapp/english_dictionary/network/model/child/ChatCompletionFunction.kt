package biz.moapp.english_dictionary.network.model.child

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChatCompletionFunction(
    val name: String? = null,
    val description: String? = null,
    //TODO: create json decode data class
    val parameters: String? = null,
)
