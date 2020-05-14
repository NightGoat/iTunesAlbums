package ru.nightgoat.itunesalbums.domain

import io.reactivex.Single
import ru.nightgoat.itunesalbums.data.model.ApiAnswer

interface Repository {
    fun getAlbumsList(name: String) : Single<ApiAnswer>
    fun getAlbum(id: Long) : Single<ApiAnswer>
}