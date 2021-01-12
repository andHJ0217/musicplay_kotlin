package hj.dev.floplayer_dev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hj.dev.floplayer_dev.fragment.LyricsFragment
import hj.dev.floplayer_dev.fragment.MusicPlayFragment

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