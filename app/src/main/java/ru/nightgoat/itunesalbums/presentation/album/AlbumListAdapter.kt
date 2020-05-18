package ru.nightgoat.itunesalbums.presentation.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nightgoat.itunesalbums.data.model.AlbumResult

/**
 * Адаптер для RecyclerView, который содержит список песен.
 * @author NightGoat
 */
class AlbumListAdapter : RecyclerView.Adapter<AlbumViewHolder>() {

    private var list: List<AlbumResult> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AlbumViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album: AlbumResult = list[position]
        holder.bind(album)
    }

    fun setList(list: List<AlbumResult>) {
        this.list = list
        notifyDataSetChanged()
    }

}