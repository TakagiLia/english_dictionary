package biz.moapp.english_dictionary.data.json_row

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Json(name = "synonym")
data class Synonym(
    val word: String,
    @Json(name = "mean") val japaneseMeaning: String
)
