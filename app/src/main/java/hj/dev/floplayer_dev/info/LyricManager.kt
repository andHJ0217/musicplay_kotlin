package hj.dev.floplayer_dev.info


class LyricManager private constructor() { //constructor :: 주 생성자를 나타내기 위한 키워드

    companion object {
        @Volatile
        private var instance: LyricManager? = null

        @JvmStatic
        fun getInstance(): LyricManager =
            instance ?: synchronized(this) {
                instance ?: LyricManager().also {
                    instance = it
                }
            }
    }

    private val lyrics = mutableListOf<LyricData>()

    fun add(lyric: LyricData) = lyrics.add(lyric)

    fun sort() = lyrics.sort()

    fun clear() = lyrics.clear()

    fun get(idx: Int): LyricData? {
        if (isRange(idx))
            return lyrics[idx]
        return null
    }

    fun find(time: Long): Pair<Int, Int> {
        var l = 0
        var r = lyrics.lastIndex
        while (l < r) {
            val m = (l + r) / 2
            if (lyrics[m].time < time)
                l = m + 1
            else
                r = m
        }
        return Pair(l - 1, l)
    }

    private fun isRange(idx: Int): Boolean {
        if (0 <= idx && idx <= lyrics.lastIndex)
            return true
        return false
    }

    fun last() = lyrics.lastIndex

    fun getCurrentLyrics() = lyrics
}