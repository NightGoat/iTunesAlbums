package ru.nightgoat.itunesalbums.presentation.search

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_album.*
import kotlinx.android.synthetic.main.fragment_search.*
import ru.nightgoat.itunesalbums.R


class SearchFragment : Fragment(), FragmentCallbacks {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private val viewModel: SearchViewModel by activityViewModels()
    private val listAdapter = SearchListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedInstanceState?.let {
            frag_search_edit.setText(it.getString("search"))
        }
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
            if (actionId == EditorInfo.IME_ACTION_SEARCH ) {
                frag_search_edit.clearFocus()
                frag_search_progress.requestFocus()
                viewModel.searchAlbum(frag_search_edit.text.toString())
            }
            false
        }
        frag_search_edit.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                frag_search_edit.clearFocus()
                frag_search_progress.requestFocus()
                viewModel.searchAlbum(frag_search_edit.text.toString())
                val imm = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view?.windowToken, 0)
                view?.clearFocus()
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        frag_search_edit?.text?.let {
            outState.putString("search", frag_search_edit.text.toString())
        }
    }

    override fun goToAlbumFragment(albumId: Long) {
        val mainActivity = activity as FragmentCallbacks
        mainActivity.goToAlbumFragment(albumId)
    }
}
