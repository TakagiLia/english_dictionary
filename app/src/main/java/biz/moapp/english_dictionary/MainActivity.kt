package biz.moapp.english_dictionary

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.databinding.DataBindingUtil
import biz.moapp.english_dictionary.databinding.NativeAdLayoutBinding
import biz.moapp.english_dictionary.ui.base.BaseScreen
import biz.moapp.english_dictionary.ui.search_result.SearchResultViewModel
import biz.moapp.english_dictionary.ui.theme.English_dictionaryTheme
import biz.moapp.english_dictionary.ui.top.TopScreenViewModel
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.nativead.NativeAdView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var nativeAdLayoutBinding: NativeAdLayoutBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**AdMob初期化**/
        MobileAds.initialize(this)

        /**AdMob　バナー広告**/
        val banner = AdView(this)
        banner.setAdSize(AdSize.BANNER)
        banner.adUnitId = getString(R.string.banner_ad_id_test)
        banner.loadAd(AdRequest.Builder().build())

        /**ネイティブ広告の初期化**/
        initNativeAd()

        /**AssetからCSV読み込み**/
        val topScreenViewModel: TopScreenViewModel by viewModels()
        topScreenViewModel.readCsvDataFromAsset(this)

        val searchResultViewModel: SearchResultViewModel by viewModels()

        enableEdgeToEdge()
        setContent {
            English_dictionaryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BaseScreen(topScreenViewModel, searchResultViewModel, banner,
                        nativeAdView = nativeAdLayoutBinding!!.root as NativeAdView)
                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        /**広告の解放**/
        (nativeAdLayoutBinding?.root as? NativeAdView)?.destroy()
    }

    /**ネイティブ広告の初期化**/
    private fun initNativeAd() {
        nativeAdLayoutBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.native_ad_layout,
            null,
            false
        )
        AdLoader.Builder(this, getString(R.string.admob_native_unit_id_test))
            .forNativeAd { ad ->
                val nativeAdView = nativeAdLayoutBinding!!.root as NativeAdView

                nativeAdView.mediaView = nativeAdLayoutBinding!!.adMedia
                nativeAdLayoutBinding!!.adMedia.setImageScaleType(ImageView.ScaleType.FIT_CENTER)
                nativeAdView.headlineView = nativeAdLayoutBinding!!.adHeadline
                nativeAdLayoutBinding!!.adHeadline.text = ad.headline
                nativeAdView.bodyView = nativeAdLayoutBinding!!.adBody
                nativeAdLayoutBinding!!.adBody.text = ad.body
                nativeAdView.adChoicesView = nativeAdLayoutBinding!!.adChoices
                nativeAdView.advertiserView = nativeAdLayoutBinding!!.adAdvertiser
                nativeAdLayoutBinding!!.adAdvertiser.text = ad.advertiser
                nativeAdView.setNativeAd(ad)
            }.build().loadAd(AdRequest.Builder().build())
    }
}