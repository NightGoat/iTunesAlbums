package ru.nightgoat.itunesalbums.presentation

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_album.*
import ru.nightgoat.itunesalbums.R
import ru.nightgoat.itunesalbums.presentation.album.AlbumFragment

class AlbumActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        if (resources.configuration.orientation
            == Configuration.ORIENTATION_LANDSCAPE
            && isLarge()) {
            finish()
            return
        }

        if (savedInstanceState == null) {
            val albumFragment = AlbumFragment.newInstance(intent.getLongExtra("albumId", -1))
            supportFragmentManager
                .beginTransaction()
                .add(R.id.album_fragment_container, albumFragment)
                .commit()
        }

        activity_album_toolbar.setOnClickListener {
            finish()
        }
    }

    private fun isLarge() : Boolean {
        return ((resources.configuration.screenLayout
                and Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE)
    }
}
