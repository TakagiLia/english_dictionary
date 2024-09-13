package biz.moapp.english_dictionary.network

import android.util.Log
import biz.moapp.english_dictionary.OPENAI_API_KEY
import biz.moapp.english_dictionary.OPEN_AI_API_BASE_URL
import biz.moapp.english_dictionary.network.model.ChatCompletions
import biz.moapp.english_dictionary.network.model.child.ChatMessage
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

interface RetrofitOpenAiApi {
    @Headers("Authorization: Bearer $OPENAI_API_KEY")
    @POST("v1/chat/completions")
    suspend fun completion(@Body request : ChatCompletions.Request) : ChatCompletions.Response.Success
}

@Singleton
class RetrofitOpenAiNetwork @Inject constructor(moshi: Moshi):OpenAiDataSource{

    /**HTTP通信ライブラリ(OkHttp)を使って、HTTPリクエストとレスポンスの内容をログに出力するための設定**/
    val client = OkHttpClient.Builder().readTimeout(30, TimeUnit.SECONDS).apply {
        val interceptor = HttpLoggingInterceptor().apply {
            /**ログレベルの設定**/
            level = HttpLoggingInterceptor.Level.BODY
        }
        addInterceptor(interceptor)
    }.build()

    /**Retrofitを使ってAPIのベースURLやデータ変換方法などを設定**/
    private val network = Retrofit.Builder()
        .baseUrl(OPEN_AI_API_BASE_URL)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(RetrofitOpenAiApi::class.java)

    override suspend fun responseCompletions(request: ChatCompletions.Request): ChatCompletions.Response {
        Log.d("--RetrofitOpenAiNetwork","responseCompletions")
       return try {
           network.completion(request)
       }catch(ex : Exception){
           Log.d("--RetrofitOpenAiNetwork","Error:${ex.message}",ex)
           ChatCompletions.Response.Failure(ex)
       }
    }

    fun createJson(word: String) = ChatCompletions.Request(
        model = "gpt-4o-mini",
        messages = listOf(
            ChatMessage(
                role = "user",
                content = "Please enter the following information for the English word \"$word\" in JSON format without line breaks.\n" +
                        "- Easy-to-understand Japanese meaning (key name: japanese_mean, list-type string)\n" +
                        "- English example sentences (10, key name: example)\n" +
                        "- English synonyms (key name: synonym) (key name: word) and their Japanese meanings (key name: mean)\n" +
                        "- English antonyms (key name: antonyms) (key name: word) and their Japanese meanings (key name: mean)\n" +
                        "- Easy-to-understand explanations of English word origins in Japanese (key name: word_roots)"
            ),
        ),
    )
}