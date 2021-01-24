package hj.dev.floplayer_dev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.exoplayer2.MediaItem
import hj.dev.floplayer_dev.fragment.LyricsFragment
import hj.dev.floplayer_dev.fragment.MusicPlayFragment

class MainActivity : AppCompatActivity() {
    val viewModel: MainViewModel  by viewModels()
    val musicPlayFragment: MusicPlayFragment by lazy {
        MusicPlayFragment()
    }
    val lyricsFragment: LyricsFragment by lazy {
        LyricsFragment()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FragmentConverter.build(musicPlayFragment, lyricsFragment)
          .convert(this, FragmentType.MusicPlay, true)

        musicPlayFragment.onMusicInfoReceivedListener =
            object : MusicPlayFragment.OnMusicInfoReceivedListener {
                override fun onMusicInFoReceived(file: String, lyrics: String) {
                    viewModel.apply {
                        if (this.file != null) return
                        this.file = file
//                        setLyrics(lyrics)
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
    }
    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()

    }


}