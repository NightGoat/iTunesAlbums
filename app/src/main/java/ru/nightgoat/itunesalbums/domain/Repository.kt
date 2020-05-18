package ru.nightgoat.itunesalbums.domain

import io.reactivex.Single
import ru.nightgoat.itunesalbums.data.model.AlbumResult

/**
 * Интерфейс взаимодействия с репозиторием
 * @author NightGoat
 */
interface Repository {
    /**
     * Возвращает список альбомов по запросу.
     * @param name название альбома
     */
    fun getAlbumsList(name: String): Single<List<AlbumResult>>

    /**
     * Возвращает описание альбома.
     * @param id id альбома
     */
    fun getAlbum(id: Long): Single<List<AlbumResult>>
}