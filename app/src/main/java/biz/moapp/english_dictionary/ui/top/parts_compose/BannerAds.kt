package biz.moapp.english_dictionary.ui.top.parts_compose

import android.widget.FrameLayout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun BannerAds(banner: AdView, maxHeight: Dp){
    /**画面全体を取得　全体の８割の高さのパディング入れる様にする**/
    val screenHeight = (maxHeight  * 0.9f)

    AndroidView(
        modifier = Modifier.fillMaxWidth().padding(top = screenHeight),
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