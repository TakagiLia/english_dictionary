package biz.moapp.english_dictionary.network

import biz.moapp.english_dictionary.model.ChatCompletions

/**APIとの通信方法を定義**/
interface OpenAiDataSource {

    /**
     *responseCompletions.RequestはAPIへのリクエスト(質問内容など)を表す型
     *responseCompletions.ResponseはAPIからのレスポンス(回答など)を表す型
     * **/
    suspend fun responseCompletions(request: ChatCompletions.Request):ChatCompletions.Response
}