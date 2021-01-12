package hj.dev.floplayer_dev.info


internal class LyricData (val time: Long, val content: String) : Comparable<LyricData> {
    override fun compareTo(other: LyricData): Int {
        return compareValues(this.time, other.time)
    }
}