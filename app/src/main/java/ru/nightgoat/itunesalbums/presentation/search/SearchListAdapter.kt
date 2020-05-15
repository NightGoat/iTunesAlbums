package ru.nightgoat.itunesalbums.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nightgoat.itunesalbums.data.model.Result

class SearchListAdapter(private val fragment: FragmentCallbacks)
    : RecyclerView.Adapter<SearchViewHolder>() {

    private var list: List<Result> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SearchViewHolder(
            inflater,
            parent
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val album : Result = list[position]
        holder.bind(album, fragment)
    }

    fun setList(list: List<Result>) {
        this.list = list
        notifyDataSetChanged()
    }

}