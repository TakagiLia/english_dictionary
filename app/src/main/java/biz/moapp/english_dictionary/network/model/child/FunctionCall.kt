package biz.moapp.english_dictionary.network.model.child

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FunctionCall(
    val name: String? = null,
    val arguments: String? = null
)
