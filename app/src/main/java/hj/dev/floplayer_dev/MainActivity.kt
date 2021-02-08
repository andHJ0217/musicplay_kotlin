package hj.dev.floplayer_dev

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.google.android.exoplayer2.MediaItem
import hj.dev.floplayer_dev.fragment.LyricsFragment
import hj.dev.floplayer_dev.fragment.MusicPlayFragment
import hj.dev.floplayer_dev.info.LyricManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val viewModel: MainViewModel  by viewModels()

    val musicPlayFragment: MusicPlayFragment by lazy {
        MusicPlayFragment()
    }
    val lyricsFragment: LyricsFragment by lazy {
        LyricsFragment()
    }
    var prevTextView: TextView? = null
    var curTextView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FragmentConverter.build(musicPlayFragment, lyricsFragment)
          .convert(this, FragmentType.MusicPlay, true)
        playerView.setProgressUpdateListener { position, _ ->
            val (curIdx, nextIdx) = viewModel.findLowerBoundOfTime(position)

            if (musicPlayFragment.isAdded) {
                musicPlayFragment.viewModel.updateLyric(curIdx, nextIdx)
            }

            if (lyricsFragment.isAdded) {
                val textViewList = lyricsFragment.lyricTextViewList

                if (textViewList.isNotEmpty() && curTextView == textViewList[curIdx])
                    return@setProgressUpdateListener

                prevTextView?.let {
                    setTextViewStyle(
                        it,
                        Typeface.DEFAULT,
                        ContextCompat.getColor(this, R.color.textDefaultColor)
                    )
                }
                curTextView = textViewList[curIdx]
                setTextViewStyle(
                    curTextView ?: return@setProgressUpdateListener,
                    Typeface.DEFAULT_BOLD,
                    ContextCompat.getColor(this, R.color.textAccentColor)
                )
                prevTextView = curTextView
            }
        }
        musicPlayFragment.onMusicInfoReceivedListener =
            object : MusicPlayFragment.OnMusicInfoReceivedListener {
                override fun onMusicInFoReceived(file: String, lyrics: String) {
                    viewModel.apply {
                        if (this.file != null) return
                        this.file = file
                        setLyrics(lyrics)
                        player?.let {
                            val url = this.file ?: return
                            val mediaItem = MediaItem.fromUri(url)
                            it.setMediaItem(mediaItem)
                            it.prepare()
                            it.seekTo(currentWindow, playbackPosition)
                            it.playWhenReady = playWhenReady
                        }
                    }
                }
            }

        lyricsFragment.onTimeSelectedListener = object : LyricsFragment.OnTimeSelectedListener {
            override fun onTimeSelected(changedTime: Long) {
                viewModel.player?.seekTo(changedTime + 1L)
            }
        }

        lyricsFragment.onBackKeyPressedListener = object : LyricsFragment.OnBackKeyPressedListener {
            override fun onBackKeyPressed() {
                this@MainActivity.onBackPressed()
            }
        }
    }
    override fun onResume() {
        super.onResume()


    }

    override fun onPause() {
        super.onPause()

    }

    private fun setTextViewStyle(textView: TextView, typeface: Typeface, color: Int) {
        textView.typeface = typeface
        textView.setTextColor(color)
    }
}