package ru.nightgoat.itunesalbums.presentation.album

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_album.*
import ru.nightgoat.itunesalbums.R
import ru.nightgoat.itunesalbums.presentation.search.FragmentChanger

/**
 * Фрагмент с описанием альбома и списком его треков. Создается при клике на альбом в списке
 * [ru.nightgoat.itunesalbums.presentation.search.SearchFragment]
 */
class AlbumFragment : Fragment() {

    companion object {
        fun newInstance() = AlbumFragment()

        val TAG = AlbumFragment::class.java.name
    }

    private val viewModel: AlbumViewModel by activityViewModels()
    private val listAdapter = AlbumListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        observeViewModelLiveData()
        backBtnClickListener()
        getAlbum()
    }

    private fun initRecycler() {
        frag_album_recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    /**
     * Запрос для вью модели получить описание альбома.
     */
    private fun getAlbum() {
        arguments?.let {
            viewModel.getAlbumInfo(it.getLong("albumId"))
        }
    }

    private fun observeViewModelLiveData() {
        with(viewModel) {
            toastLiveData.observe(viewLifecycleOwner, Observer {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            })

            resultListLiveData.observe(viewLifecycleOwner, Observer {
                val albumInfo = it[0]
                Glide.with(context).load(albumInfo.artworkUrl100).into(frag_album_cover)
                frag_album_artist.text = albumInfo.artistName
                frag_album_name.text = albumInfo.collectionName
                frag_album_genre.text = albumInfo.primaryGenreName
                frag_album_year.text = albumInfo.releaseDate?.subSequence(0, 4)
                frag_album_toolbar.title = "${albumInfo.collectionName} by ${albumInfo.artistName}"
                listAdapter.setList(it.subList(1, it.size))
            })

            isProgressBarVisibleLiveData.observe(viewLifecycleOwner, Observer {
                if (it == true) {
                    frag_album_group.visibility = View.INVISIBLE
                    frag_album_group.requestLayout()
                    frag_album_progressBar.visibility = View.VISIBLE
                } else {
                    frag_album_group.visibility = View.VISIBLE
                    frag_album_group.requestLayout()
                    frag_album_progressBar.visibility = View.INVISIBLE
                }
                Log.d(TAG, "isProgressBarVisible.value = ${isProgressBarVisibleLiveData.value}" +
                        ", group.visibility = ${frag_album_group.visibility}" +
                        ", progressBar.visibility = ${frag_album_progressBar.visibility}")
            })
        }
    }

    private fun backBtnClickListener() {
        frag_album_toolbar.setNavigationOnClickListener {
            val mainActivity = activity as FragmentChanger
            mainActivity.popBackStack()
        }
    }
}
