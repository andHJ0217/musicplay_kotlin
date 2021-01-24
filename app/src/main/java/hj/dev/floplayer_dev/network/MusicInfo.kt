package hj.dev.floplayer_dev.network
import com.google.gson.annotations.SerializedName
data class MusicInfo(
       @SerializedName("singer") val singer: String = "",
       @SerializedName("album") val album: String = "",
       @SerializedName("title") val title: String = "",
       @SerializedName("image") val image: String = "",
       @SerializedName("file") val file: String = "",
       @SerializedName("lyrics") val lyrics: String = "")
