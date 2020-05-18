package ru.nightgoat.itunesalbums.data.network

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.nightgoat.itunesalbums.data.model.ApiAnswer
import ru.nightgoat.itunesalbums.utils.BASE_URL

/**
 * Интерфейс взаимодействия с iTunes Api
 * @author NightGoat
 */
interface ItunesSearchApi {

    /**
     * Метод получения списка альбомов
     * @param album Название альбома
     * @param media Тип получаемого медиа (например фильм). Установлен как музыка
     * @param entity Уточняет вид медиа (актер фильма). Установлен как альбом
     * @param attribute Атрибут запроса, можно указать если нужен конкретно альбом, а не все что
     * связано с этим названием.
     * @return Single<ApiAnswer>
     */
    @GET("search?")
    fun getAlbumList(
        @Query("term") album: String,
        @Query("media") media: String = "music",
        @Query("entity") entity: String = "album",
        @Query("attribute") attribute: String = "albumTerm"
    ): Single<ApiAnswer>

    /**
     * Метод получения подробной информации об альбоме.
     * @param albumId id альбома
     * @param entity Уточняет вид медиа (актер фильма). Установлен как песни
     */
    @GET("lookup?")
    fun getAlbum(
        @Query("id") albumId: Long,
        @Query("entity") entity: String = "song"
    ): Single<ApiAnswer>

    companion object {
        fun create(): ItunesSearchApi {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ItunesSearchApi::class.java)
        }
    }
}