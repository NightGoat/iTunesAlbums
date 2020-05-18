package ru.nightgoat.itunesalbums.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import ru.nightgoat.itunesalbums.R
import ru.nightgoat.itunesalbums.presentation.album.AlbumFragment

/**
 * Активити экрана поиска. Если экран большой и в альбомной ориентации то показывает два фрагмента
 * одновременно.
 * @author NightGoat
 * isLandOrientationOnBugScreen - параметр определяющий есть ли на экране контейнер для
 * фрагмента с альбомом. Определяется макетом layout.
 */
class MainActivity : AppCompatActivity(), OnItemClickListener {

    var isLandOrientationOnBigScreen: Boolean = true
    var albumId = -1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) albumId = savedInstanceState.getLong("albumId")

        isLandOrientationOnBigScreen =
            (findViewById<FragmentContainerView>(R.id.activity_main_fragment_container) != null)

        if (isLandOrientationOnBigScreen) showAlbumFragment()
    }

    private fun showAlbumFragment() {
        if (isLandOrientationOnBigScreen) {
            var albumFragment =
                supportFragmentManager.findFragmentById(R.id.activity_main_fragment_container)
                        as AlbumFragment?
            if (albumFragment == null || albumFragment.getAlbumId() != albumId) {
                albumFragment = AlbumFragment.newInstance(albumId)
                changeFragment(albumFragment)
            }
        } else {
            startActivity(
                Intent(this, AlbumActivity::class.java)
                    .putExtra("albumId", albumId)
            )
        }
    }

    override fun onItemClick(albumId: Long) {
        this.albumId = albumId
        showAlbumFragment()
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )
            .replace(R.id.activity_main_fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong("albumId", albumId)
    }
}
