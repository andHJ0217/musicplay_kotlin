package hj.dev.floplayer_dev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer

class MainActivity : AppCompatActivity() {
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

    }
}