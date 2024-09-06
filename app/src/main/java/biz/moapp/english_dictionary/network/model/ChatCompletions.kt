package biz.moapp.english_dictionary.network.model

import biz.moapp.english_dictionary.network.model.child.ChatChoice
import biz.moapp.english_dictionary.network.model.child.ChatMessage
import biz.moapp.english_dictionary.network.model.child.Usage
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**APIに送信するリクエストデータの構造を定義 **/
sealed interface ChatCompletions {
    @JsonClass(generateAdapter = true)
    data class Request(
        val model: String,
        val messages: List<ChatMessage>,
        val temperature: Double? = null,
        @Json(name = "top_p")
        val topP: Double? = null,
        val n: Int? = null,
        val stop: List<String>? = null,
        @Json(name = "max_tokens")
        val maxTokens: Int? = null,
        @Json(name = "frequency_penalty")
        val presencePenalty: Double? = null,
        @Json(name = "logit_bias")
        val logitBias: Map<String, Int>? = null,
        val user: String? = null,
        ) : ChatCompletions

    sealed interface Response : ChatCompletions {
        @JsonClass(generateAdapter = true)
        data class Success(
            val id: String,
            val created: Int,
            val model: String,
            val choices: List<ChatChoice>,
            val usage: Usage? = null,
        ) : Response

        data class Failure(
            val exception: Exception
        ) : Response
    }
}

