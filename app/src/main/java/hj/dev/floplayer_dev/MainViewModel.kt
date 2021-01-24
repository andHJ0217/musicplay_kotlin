package hj.dev.floplayer_dev

import androidx.lifecycle.ViewModel
import com.google.android.exoplayer2.SimpleExoPlayer
import hj.dev.floplayer_dev.info.LyricManager

class MainViewModel: ViewModel() {
    var player: SimpleExoPlayer? = null
    var playWhenReady = false
    var currentWindow = 0
    var playbackPosition: Long = 0
    var file: String ?= null
    private val lyricManager = LyricManager.getInstance()


    fun findLowerBoundOfTime(time: Long): Pair<Int, Int> {
        val (idx1, idx2) = lyricManager.find(time)
        return when {
            idx1 < 0 -> Pair(idx2, idx2 + 1)
            idx2 == lyricManager.last() -> Pair(idx1, idx2)
            else -> Pair(idx1, idx2)
        }
    }
}
