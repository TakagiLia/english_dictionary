package biz.moapp.english_dictionary.model.child

import biz.moapp.transcription_app.model.child.ChatMessage
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChatChoice(
    val index: Int? = null,
    val message: ChatMessage? = null,
    val finishReason: String? = null,
)
