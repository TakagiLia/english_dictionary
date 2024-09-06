package biz.moapp.english_dictionary.network.model.child

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Usage(
    val promptTokens: Int? = null,
    val completionTokens: Int? = null,
    val totalTokens: Int? = null,
)
