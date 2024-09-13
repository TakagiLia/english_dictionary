package biz.moapp.english_dictionary.data.json_row

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Json(name = "antonyms")
data class Antonyms(
    val word: String,
    @Json(name = "mean") val japaneseMeaning: String
)
