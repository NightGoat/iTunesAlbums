package ru.nightgoat.itunesalbums.presentation.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nightgoat.itunesalbums.data.model.Results

class ListAdapter(private var list: List<Results> = listOf())
    : RecyclerView.Adapter<AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AlbumViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album : Results = list[position]
        holder.bind(album)
    }

    fun setList(list: List<Results>) {
        this.list = list
        notifyDataSetChanged()
    }

}