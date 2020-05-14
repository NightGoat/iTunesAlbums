package ru.nightgoat.itunesalbums.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.nightgoat.itunesalbums.R
import ru.nightgoat.itunesalbums.presentation.search.SearchFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(
                R.id.main_fragment_container,
                SearchFragment.newInstance()
            )
            .commit()
    }
}
