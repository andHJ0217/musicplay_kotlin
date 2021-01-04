package hj.dev.floplayer_dev

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

object FragmentConverter {
    private lateinit var fragmentTypeConverter: HashMap<FragmentType, Fragment>

    fun build(musicPlayFragment: Fragment, lyricsFragment: Fragment): FragmentConverter {
        fragmentTypeConverter = hashMapOf(
            FragmentType.MusicPlay to musicPlayFragment, FragmentType.Lyrics to lyricsFragment
        )
        return this
    }
    fun convert(
        activity: FragmentActivity,
        fragmentType: FragmentType,
        noBackStack: Boolean = false
    ) {
        val transaction = activity.supportFragmentManager.beginTransaction()
        if (!noBackStack)
            transaction.addToBackStack(null)
        transaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_out)
        transaction.replace(R.id.fragmentFrameLayout, fragmentTypeConverter[fragmentType]!!)
            .commit()
    }
}
