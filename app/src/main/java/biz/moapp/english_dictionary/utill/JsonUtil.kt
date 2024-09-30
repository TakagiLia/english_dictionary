package biz.moapp.english_dictionary.utill

import android.util.Log
import biz.moapp.english_dictionary.data.json_row.WordInfo
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object JsonUtil {
    fun convertJsonToWordInfo(rowJson:String) :Result<WordInfo>{
        return runCatching {
            val json = rowJson
                .replace("\n", "")
                .replace("  ", "")
                .replace("```json", "")
                .replace("```", "")
                .trimIndent()

            Log.d("--convertJsonToWordInfo json", "${json}")

            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            val adapter = moshi.adapter(WordInfo::class.java)

            adapter.fromJson(json)!!
        }.onFailure { e ->
            Log.e("--Error", "convertJsonToWordInfo  Message:${e.message}", e)
        }
    }

    fun convertStringToList(japaneseMeaning :String) :List<String>{
        return if(japaneseMeaning.contains("; ")){
            japaneseMeaning.split("; ")
        }else{
            listOf(japaneseMeaning)
        }
    }

    /**文字列の文章を「」に囲われている英単語ごとにセンテンスを区切り、リスト化**/
    fun replaceEnglishWordsWithNumbers(text: String): List<String> {
        val firstReplaceText = text.replace("「"," 「")
        val endReplaceText = firstReplaceText.replace("」","」 ")
        val periodReplaceText = endReplaceText.replace("。","。 ")
        val punctuationReplaceText = periodReplaceText.replace("、","、 ")
        val sentenceList = punctuationReplaceText.split(" ")
        Log.d("--sentenceList","$sentenceList")
        return sentenceList
    }
}