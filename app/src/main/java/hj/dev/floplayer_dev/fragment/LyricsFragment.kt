package hj.dev.floplayer_dev.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import hj.dev.floplayer_dev.R
import hj.dev.floplayer_dev.info.LyricManager
import kotlinx.android.synthetic.main.fragment_lyrics.*

class LyricsFragment : Fragment(){
    val lyricTextViewList = mutableListOf<TextView>()
    lateinit var onTimeSelectedListener: OnTimeSelectedListener
    lateinit var onBackKeyPressedListener: OnBackKeyPressedListener
    interface OnTimeSelectedListener {
        fun onTimeSelected(changedTime: Long)
    }

    interface OnBackKeyPressedListener {
        fun onBackKeyPressed()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
    override fun onResume() {
        super.onResume()
        val lyricInfoList = LyricManager.getInstance().getCurrentLyrics()
        for ((idx, lyricInfo) in lyricInfoList.withIndex()) {
            val textView = View.inflate(requireActivity(), R.layout.lyric_item, null) as TextView
            textView.text = lyricInfo.content.trim()
            if (textView.text.isNullOrBlank()) continue

            textView.setOnClickListener {
                if (toggleLyricButton.isChecked) {
                    onTimeSelectedListener.onTimeSelected(
                        LyricManager.getInstance().get(idx)?.time ?: return@setOnClickListener
                    )
                } else
                    onBackKeyPressedListener.onBackKeyPressed()
            }

            lyricTextViewList.add(textView)
        }
        for (textView in lyricTextViewList) {
            lyricVerticalLayout.addView(textView)
        }
    }

}
