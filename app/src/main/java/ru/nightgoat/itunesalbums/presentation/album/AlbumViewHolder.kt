package ru.nightgoat.itunesalbums.presentation.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_track.view.*
import ru.nightgoat.itunesalbums.R
import ru.nightgoat.itunesalbums.data.model.AlbumResult

/**
 * ViewHolder для RecyclerView, который содержит список песен.
 * @author NightGoat
 */
class AlbumViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_track, parent, false)) {

    fun bind(albumResult: AlbumResult) {
        itemView.album_track_count.text = albumResult.trackNumber.toString()
        itemView.album_track_name.text = albumResult.trackName
    }
}