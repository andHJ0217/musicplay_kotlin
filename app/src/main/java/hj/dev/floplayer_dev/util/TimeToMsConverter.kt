package hj.dev.floplayer_dev.util

object TimeToMsConverter {
    fun convert(time: String) : Long {
        val (min, sec, milliSec) = time.split(":").map { it.toLong() }
        return min * 60 * 1000 + sec * 1000 + milliSec
    }
}