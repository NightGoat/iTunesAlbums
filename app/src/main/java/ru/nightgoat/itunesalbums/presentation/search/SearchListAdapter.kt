package ru.nightgoat.itunesalbums.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nightgoat.itunesalbums.data.model.AlbumResult
import ru.nightgoat.itunesalbums.presentation.OnItemClickListener

/**
 * Адаптер для RecyclerView, который содержит список альбомов при поиске.
 * @author NightGoat
 * @param clickListener Интерфейс, которому передается колбэк нажатия на элемент в списке.
 */
class SearchListAdapter(private val clickListener: OnItemClickListener) :
    RecyclerView.Adapter<SearchViewHolder>() {

    private var list: List<AlbumResult> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SearchViewHolder(
            inflater,
            parent
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val album: AlbumResult = list[position]
        holder.bind(album, clickListener)
    }

    fun setList(list: List<AlbumResult>) {
        this.list = list
        notifyDataSetChanged()
    }

}