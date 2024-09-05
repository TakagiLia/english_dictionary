package biz.moapp.english_dictionary.network

import android.util.Log
import biz.moapp.english_dictionary.OPENAI_API_KEY
import biz.moapp.english_dictionary.OPEN_AI_API_BASE_URL
import biz.moapp.english_dictionary.model.ChatCompletions
import biz.moapp.english_dictionary.model.child.ChatMessage
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface RetrofitOpenAiApi {
    @Headers("Authorization: Bearer $OPENAI_API_KEY")
    @POST("v1/chat/completions")
    suspend fun completion(@Body request : ChatCompletions.Request) : ChatCompletions.Response.Success
}

class RetrofitOpenAiNetwork :OpenAiDataSource{

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
        .addConverterFactory(MoshiConverterFactory.create(
            Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        ))
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
                content = "The Japanese meaning of $word, English example sentences (10), English synonyms (including the Japanese meaning), and English co-occurrences are all answered in JSON format using a Japanese-English dictionary."
            ),
        ),
    )
}