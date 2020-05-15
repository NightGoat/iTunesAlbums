package ru.nightgoat.itunesalbums.data

import android.util.Log
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.nightgoat.itunesalbums.data.model.ApiAnswer
import ru.nightgoat.itunesalbums.data.network.ItunesSearchApi
import ru.nightgoat.itunesalbums.domain.Repository

class RepositoryImpl(private val api: ItunesSearchApi) : Repository {
    override fun getAlbumsList(name: String): Single<ApiAnswer> {
        Log.d("RepositoryImpl", "getAlbumsList")
        return api.getAlbumList(name).subscribeOn(Schedulers.io())
    }

    override fun getAlbum(id: Long): Single<ApiAnswer> {
        return api.getAlbum(id).subscribeOn(Schedulers.io())
    }
}