package biz.moapp.english_dictionary.ui.search_result.parts_compose

import android.widget.FrameLayout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import biz.moapp.english_dictionary.R
import com.google.android.gms.ads.nativead.NativeAdView

@Composable
fun NativeAds(nativeAdView: NativeAdView){
    AndroidView(modifier = Modifier.fillMaxWidth(),
        factory = { context ->

            if(nativeAdView.parent != null){
                (nativeAdView.parent as FrameLayout).removeAllViews()
            }

            FrameLayout(context).apply {
                minimumHeight = context.resources.getDimensionPixelSize(R.dimen.native_ad_height)
                addView(nativeAdView)
            }
        })
}