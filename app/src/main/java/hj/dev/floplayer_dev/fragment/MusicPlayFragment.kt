package hj.dev.floplayer_dev.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import hj.dev.floplayer_dev.FragmentConverter
import hj.dev.floplayer_dev.FragmentType
import hj.dev.floplayer_dev.R
import hj.dev.floplayer_dev.databinding.FragmentMusicPlayBinding
import hj.dev.floplayer_dev.network.FloApi
import hj.dev.floplayer_dev.network.MusicInfo
import hj.dev.floplayer_dev.view.MusicPlayFragmentViewModel
import kotlinx.android.synthetic.main.fragment_music_play.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MusicPlayFragment : Fragment(){
    private lateinit var binding: FragmentMusicPlayBinding

    lateinit var onMusicInfoReceivedListener: OnMusicInfoReceivedListener
    val viewModel: MusicPlayFragmentViewModel by viewModels()
    interface OnMusicInfoReceivedListener {
        fun onMusicInFoReceived(file: String, lyrics: String)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_music_play, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getMusic()

        lyricsLayout.setOnClickListener {
            FragmentConverter.convert(requireActivity(), FragmentType.Lyrics)
        }
    }
    private fun getMusic() {
        val call = FloApi.create().getMusicInfo()
        call.enqueue(object : Callback<MusicInfo> {
            override fun onResponse(call: Call<MusicInfo>, response: Response<MusicInfo>) {
                val musicInfo = response.body()
                musicInfo?.apply {
                    viewModel.setTitle(title)
                    viewModel.setSinger(singer)
                    viewModel.setAlbum(album)
                    viewModel.
                    setImage(image)
                    onMusicInfoReceivedListener.onMusicInFoReceived(file, lyrics)
                }
            }

            override fun onFailure(call: Call<MusicInfo>, t: Throwable) {
                Log.e("error", "${t.message}")
            }
        })
    }
}
