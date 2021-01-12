package hj.dev.floplayer_dev.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import hj.dev.floplayer_dev.R
import hj.dev.floplayer_dev.databinding.FragmentMusicBinding
import hj.dev.floplayer_dev.view.MusicPlayFragmentViewModel
import kotlinx.android.synthetic.main.fragment_music.*

class MusicPlayFragment : Fragment(){
    private lateinit var binding: FragmentMusicBinding
    val viewModel: MusicPlayFragmentViewModel by viewModels()
    interface OnMusicInfoReceivedListener {
        fun onMusicInFoReceived(file: String, lyrics: String)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_music, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }
}
