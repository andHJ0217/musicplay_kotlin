package hj.dev.floplayer_dev

import androidx.lifecycle.ViewModel
import com.google.android.exoplayer2.SimpleExoPlayer
import hj.dev.floplayer_dev.info.LyricData
import hj.dev.floplayer_dev.info.LyricManager
import hj.dev.floplayer_dev.util.TimeToMsConverter

class MainViewModel: ViewModel() {
    var player: SimpleExoPlayer? = null
    var playWhenReady = false
    var currentWindow = 0
    var playbackPosition: Long = 0
    var file: String ?= null
    private val lyricManager = LyricManager.getInstance()

    fun setLyrics(lyrics: String) {
        lyricManager.clear()
        val splitLyrics = lyrics.split("\n")
        for (lyric in splitLyrics) {
            val (time, content) = lyric.split("[", "]").filter { it.isNotEmpty() }
            lyricManager.add(LyricData(TimeToMsConverter.convert(time), content))
        }
        lyricManager.add(LyricData(Long.MAX_VALUE, " "))
        lyricManager.sort()
    }

    fun findLowerBoundOfTime(time: Long): Pair<Int, Int> {
        val (idx1, idx2) = lyricManager.find(time)
        return when {
            idx1 < 0 -> Pair(idx2, idx2 + 1)
            idx2 == lyricManager.last() -> Pair(idx1, idx2)
            else -> Pair(idx1, idx2)
        }
    }
}
