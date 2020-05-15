package ru.nightgoat.itunesalbums.presentation.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nightgoat.itunesalbums.R
import ru.nightgoat.itunesalbums.data.model.Result
import kotlinx.android.synthetic.main.item_track.view.*

class AlbumViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_track, parent, false)) {

    fun bind(result: Result) {
        itemView.album_track_count.text = result.trackNumber.toString()
        itemView.album_track_name.text = result.trackName
    }
}