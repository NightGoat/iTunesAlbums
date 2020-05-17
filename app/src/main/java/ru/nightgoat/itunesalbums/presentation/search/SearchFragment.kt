package ru.nightgoat.itunesalbums.presentation.search

import android.app.Activity
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_search.*
import ru.nightgoat.itunesalbums.R

/**
 * Основной фрагмент приложения. Содержит строку поиска альбомов и список результатов поиска.
 * @author NightGoat
 */
class SearchFragment : Fragment(), OnItemClickListener {

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
        searchClearClickListener()
        initRecycler()
    }

    private fun initRecycler() {
        frag_search_recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }
    }

    /**
     * Метод обработки строки поиска. setOnEditorActionListener обрабатывает кнопку поиск на android клавиатуре.
     * setOnKeyListener обрабатывает кнопку Enter на физической клавиатуре.
     */
    private fun searchClickListener() {
        frag_search_edit.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ) {
                frag_search_edit.clearFocus()
                frag_search_progress.requestFocus()
                viewModel.searchAlbum(frag_search_edit.text.toString())
            }
            false
        }
        frag_search_edit.setOnKeyListener { _, keyCode, _ ->
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

    /**
     * Метод стирающий список при пустой поисковой строки
     */
    private fun searchClearClickListener() {
        frag_search_edit.doAfterTextChanged {
            if (it.isNullOrEmpty()) listAdapter.setList(listOf())
        }
    }

    private fun observeViewModelLiveData() {
        lifecycle.addObserver(viewModel)
        with(viewModel) {
            toastLiveData.observe(viewLifecycleOwner, Observer {
                if (it != null) Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
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

    override fun onItemClick(albumId: Long) {
        val mainActivity = activity as OnItemClickListener
        mainActivity.onItemClick(albumId)
    }
}
