package ru.nightgoat.itunesalbums.presentation.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_search.*
import ru.nightgoat.itunesalbums.R
import ru.nightgoat.itunesalbums.presentation.album.ListAdapter


class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private val viewModel: SearchViewModel by activityViewModels()
    private val listAdapter = ListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModelLiveData()
        searchClickListener()
        initRecycler()

    }

    private fun initRecycler() {
        frag_search_recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }
    }

    private fun searchClickListener() {
        frag_search_edit.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                Log.d("SearchFragment", "Action DONE with ${frag_search_edit.text.toString()}")
                frag_search_edit.clearFocus()
                viewModel.searchAlbum(frag_search_edit.text.toString())
            }
            false
        }
    }

    private fun observeViewModelLiveData() {
        with(viewModel) {
            toastLiveData.observe(viewLifecycleOwner, Observer {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            })

            resultListLiveData.observe(viewLifecycleOwner, Observer {
                listAdapter.setList(it)
            })

            isProgressBarVisibleLiveData.observe(viewLifecycleOwner, Observer {
                if (it == true) {
                    frag_search_progress.visibility = View.VISIBLE
                    frag_search_recycler.visibility = View.GONE
                } else {
                    frag_search_progress.visibility = View.GONE
                    frag_search_recycler.visibility = View.VISIBLE
                }
            })
        }
    }
}
