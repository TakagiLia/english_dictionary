package biz.moapp.english_dictionary

import android.content.Context
import biz.moapp.english_dictionary.data.Language

object FileUtil {

    private const val FILE_NAME = "EnglishDictionary.csv"

    /**assetsフォルダからCSVデータを読み込む関数**/
    fun readCsvDataFromAssets(context: Context): List<Language> {
        val assetManager = context.assets
        val inputStream = assetManager.open(FILE_NAME)
        val linesList = mutableListOf<String>()
        inputStream.bufferedReader().useLines { lines ->
            lines.drop(1).forEach { line ->
                linesList.add(line)
            }
        }
        val list = convertToLanguage(linesList)
        return list
    }

    /**取得したCSVデータの一行をLanguageデータクラスに変換する関数**/
    private fun convertToLanguage(dataList: List<String>) : List<Language> {
        return dataList.map { item ->
            val (num, englishMean, japaneseMean) = item.split(",").map { it.trim() }
            Language(num, englishMean, japaneseMean)
        }
    }
}