package biz.moapp.english_dictionary

import android.util.Log
import biz.moapp.english_dictionary.data.json_row.WordInfo
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object JsonUtil {
    fun convertJsonToWordInfo(rowJson:String) :WordInfo?{
        try {
            val json = rowJson
                .replace("\n", "")
                .replace("  ", "")
                .replace("```json", "")
                .trimIndent()

            Log.d("--convertJsonToWordInfo json", "${json}")

            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            val adapter = moshi.adapter(WordInfo::class.java)

            return adapter.fromJson(json)
        }catch (e:Exception){
            Log.e("--Error convertJsonToWordInfo","Message: ${e.message}",e)
        }
       return null
    }

    fun convertStringToList(japaneseMeaning :String) :List<String>{
        return if(japaneseMeaning.contains("; ")){
            japaneseMeaning.split("; ")
        }else{
            listOf(japaneseMeaning)
        }
    }
}