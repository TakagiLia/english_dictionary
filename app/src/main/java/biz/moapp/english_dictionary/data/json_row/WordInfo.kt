package biz.moapp.english_dictionary.data.json_row

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WordInfo(
    @Json(name = "japanese_mean") val japaneseMeaning: List<String>,
    @Json(name = "example") val exampleSentences: List<String>,
    @Json(name = "synonym") val synonyms: List<Synonym>,
    @Json(name = "antonyms") val antonyms: List<Antonyms>,
    @Json(name = "word_roots") val wordRoots: String,
)
