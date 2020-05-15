package ru.nightgoat.itunesalbums.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_album_card.view.*
import ru.nightgoat.itunesalbums.R
import ru.nightgoat.itunesalbums.data.model.Result

class SearchViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_album_card, parent, false)) {

    fun bind(result: Result, fragment: FragmentCallbacks) {
        Glide.with(itemView.context)
            .load(result.artworkUrl100)
            .into(itemView.item_album_card_cover)
        itemView.item_album_card_artist.text = result.artistName
        itemView.item_album_card_name.text = result.collectionName
        itemView.setOnClickListener {
            fragment.goToAlbumFragment(result.collectionId)
        }
    }
}