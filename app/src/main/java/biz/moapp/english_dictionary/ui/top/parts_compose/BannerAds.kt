package biz.moapp.english_dictionary.ui.top.parts_compose

import android.widget.FrameLayout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun BannerAds(banner: AdView){
    AndroidView(
        modifier = Modifier.fillMaxWidth(),
        factory = { context ->
            if(banner.parent != null){
                (banner.parent as FrameLayout).removeAllViews()
            }
            FrameLayout(context).apply {
                minimumHeight = AdSize.BANNER.getHeightInPixels(context)
                minimumWidth = AdSize.FULL_WIDTH
                addView(banner)
            }
        }
    )
}