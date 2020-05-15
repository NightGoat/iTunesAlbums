package ru.nightgoat.itunesalbums.domain

import io.reactivex.Single
import ru.nightgoat.itunesalbums.data.model.ApiAnswer

/**
 * Интерфейс взаимодействия с репозиторием
 * @author NightGoat
 */
interface Repository {
    /**
     * Возвращает список альбомов по запросу.
     * @param name название альбома
     */
    fun getAlbumsList(name: String) : Single<ApiAnswer>

    /**
     * Возвращает описание альбома.
     * @param id id альбома
     */
    fun getAlbum(id: Long) : Single<ApiAnswer>
}