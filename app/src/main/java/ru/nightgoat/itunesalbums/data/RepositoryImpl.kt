package ru.nightgoat.itunesalbums.data

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.nightgoat.itunesalbums.data.model.AlbumResult
import ru.nightgoat.itunesalbums.data.network.ItunesSearchApi
import ru.nightgoat.itunesalbums.domain.Repository

/**
 * Реализация интерфейса репозитория.
 * @see [Repository]
 * @author NightGoat
 * @param api Апи предоставляемый Retrofit
 */
class RepositoryImpl(private val api: ItunesSearchApi) : Repository {

    /**
     * Возвращает список альбомов по запросу. Выполняет асинхронно
     * @param name название альбома
     */
    override fun getAlbumsList(name: String): Single<List<AlbumResult>> {
        return api.getAlbumList(name)
            .subscribeOn(Schedulers.io())
            .map {
                it.albumResults.sortedBy { result ->
                    result.collectionName
                }
            }
    }

    /**
     * Возвращает описание альбома. Выполняет асинхронно
     * @param id id альбома
     */
    override fun getAlbum(id: Long): Single<List<AlbumResult>> {
        return api.getAlbum(id)
            .subscribeOn(Schedulers.io())
            .map {
                it.albumResults
            }
    }
}