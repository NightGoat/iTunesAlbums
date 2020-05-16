package ru.nightgoat.itunesalbums.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.nightgoat.itunesalbums.R
import ru.nightgoat.itunesalbums.presentation.album.AlbumFragment
import ru.nightgoat.itunesalbums.presentation.search.FragmentCallbacks
import ru.nightgoat.itunesalbums.presentation.search.FragmentChanger
import ru.nightgoat.itunesalbums.presentation.search.SearchFragment

/**
 * Главная активити всего приложения.
 * @author NightGoat
 */
class MainActivity : AppCompatActivity(), FragmentChanger, FragmentCallbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_fragment_container, SearchFragment.newInstance())
                .commit()
        }
    }

    override fun goToAlbumFragment(albumId: Long) {
        val arguments = Bundle()
        arguments.putLong("albumId", albumId)
        val albumFragment = AlbumFragment.newInstance()
        albumFragment.arguments = arguments
        changeFragment(albumFragment)
    }

    override fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right)
            .replace(R.id.main_fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun popBackStack() {
        supportFragmentManager.popBackStack()
    }

}
