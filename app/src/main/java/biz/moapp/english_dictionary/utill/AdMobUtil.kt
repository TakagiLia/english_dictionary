package biz.moapp.english_dictionary.utill

import biz.moapp.english_dictionary.BuildConfig

/**デバッグの場合はテスト用、本番では本番用IDを利用する様にする**/
object AdMobUtil {

    /**バナー広告IDの取得**/
    fun getBannerAdUnitId(): String {
        return if (BuildConfig.DEBUG) {
            "ca-app-pub-3940256099942544/6300978111"
        } else {
            "ca-app-pub-5071120464333172/9291165767"
        }
    }

    /**ネイティブ広告IDの取得**/
    fun getNativeAdUnitId(): String {
        return if (BuildConfig.DEBUG) {
            "ca-app-pub-3940256099942544/2247696110"
        } else {
            "ca-app-pub-5071120464333172/6206709563"
        }
    }
}