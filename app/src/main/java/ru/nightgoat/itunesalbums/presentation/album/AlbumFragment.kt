package ru.nightgoat.itunesalbums.presentation.album

import android.content.Intent
import android.net.Uri
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

/**
 * Фрагмент с описанием альбома и списком его треков.
 * @author NightGoat
 */
class AlbumFragment : Fragment() {

    companion object {
        fun newInstance(albumId: Long): AlbumFragment {
            val albumFragment = AlbumFragment()
            val args = Bundle()
            args.putLong("albumId", albumId)
            albumFragment.arguments = args
            return albumFragment
        }
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
        getAlbum()
    }

    private fun onArtistClickLister(url: String) {
        frag_album_artist.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }
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
        getAlbumId()?.let { viewModel.getAlbumInfo(it) }
    }

    /**
     * Метод возвращающий из аргументов id альбома
     */
    fun getAlbumId(): Long? {
        return arguments?.getLong("albumId", -1L)
    }

    private fun observeViewModelLiveData() {
        lifecycle.addObserver(viewModel)
        with(viewModel) {
            toastLiveData.observe(viewLifecycleOwner, Observer {
                if (it != null) Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            })

            resultListLiveData.observe(viewLifecycleOwner, Observer {
                val albumInfo = it[0]
                Glide.with(context).load(albumInfo.artworkUrl100).into(frag_album_cover)
                frag_album_artist.text = albumInfo.artistName
                onArtistClickLister(albumInfo.artistViewUrl)
                frag_album_name.text = albumInfo.collectionName
                frag_album_genre.text = albumInfo.primaryGenreName
                frag_album_year.text = albumInfo.releaseDate?.subSequence(0, 4)
                listAdapter.setList(it.subList(1, it.size))
            })

            isProgressBarVisibleLiveData.observe(viewLifecycleOwner, Observer {
                if (it == true) {
                    frag_album_progressBar.visibility = View.VISIBLE
                } else {
                    frag_album_progressBar.visibility = View.INVISIBLE
                }
            })

        }
    }
}
