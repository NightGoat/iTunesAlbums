package ru.nightgoat.itunesalbums.presentation.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.nightgoat.itunesalbums.R
import ru.nightgoat.itunesalbums.data.model.Results
import kotlinx.android.synthetic.main.item_album_card.view.*

class AlbumViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_album_card, parent, false)) {

    fun bind(results: Results) {
        Glide.with(itemView.context)
            .load(results.artworkUrl100)
            .into(itemView.item_album_card_cover)
        itemView.item_album_card_artist.text = results.artistName
        itemView.item_album_card_name.text = results.collectionName
    }
}