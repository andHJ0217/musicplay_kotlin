package hj.dev.floplayer_dev.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hj.dev.floplayer_dev.info.LyricManager

class MusicPlayFragmentViewModel: ViewModel() {
    private val _title: MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            postValue("")
        }
    }
    val title: LiveData<String>
        get() = _title

    private val _singer: MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            postValue("")
        }
    }
    val singer: LiveData<String>
        get() = _singer

    private val _image: MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            postValue("")
        }
    }
    val image: LiveData<String>
        get() = _image

    private val _album: MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            postValue("")
        }
    }
    val album: LiveData<String>
        get() = _album

    private val _curLyric: MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            postValue("")
        }
    }
    val curLyric: LiveData<String>
        get() = _curLyric

    private val _nextLyric: MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            postValue("")
        }
    }
    val nextLyric: LiveData<String>
        get() = _nextLyric

    fun setTitle(title: String) {
        _title.value = title
    }

    fun setSinger(singer: String) {
        _singer.value = singer
    }

    fun setImage(image: String) {
        _image.value = image
    }

    fun setAlbum(file: String) {
        _album.value = file
    }

    fun updateLyric(curIdx: Int, nextIdx: Int) {
        val lyricManager = LyricManager.getInstance()
        _curLyric.value = lyricManager.get(curIdx)?.content ?: lyricManager.get(nextIdx)?.content
        _nextLyric.value = lyricManager.get(nextIdx)?.content ?: ""
    }
}