package ru.nightgoat.itunesalbums.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Класс данных, представляющий элемент списка результата запроса. iTunes Api возвращает все результаты
 * запроса в одном списке. В начале идет альбом, затем треки. Некоторые параметры отмечены
 * как nullable, потому что при запросе списка альбомов их может не быть.
 * @author NightGoat
 * @param wrapperType тип возвращаемого объекта. Collection - альбом, Track - трек
 * @param kind тип трека. Может быть песней или клипом
 * @param collectionId Id альбома
 * @param artistName имя исполнителя
 * @param collectionName название альбома
 * @param artworkUrl100 ссылка на обложку 100х100
 * @param trackNumber номер трека в альбоме
 * @param releaseDate дата релиза. В формате YYYY-MM-DD HH:MM:SS (2006-10-02T07:00:00Z)
 * @param primaryGenreName жанр альбома
 * @param artistViewUrl url ссылка на исполнителя на сайте apple
 */
data class AlbumResult(

    @SerializedName("wrapperType")
    @Expose
    var wrapperType: String,

    @SerializedName("kind")
    @Expose
    var kind: String?,

    @SerializedName("collectionId")
    @Expose
    var collectionId: Long,

    @SerializedName("artistName")
    @Expose
    var artistName: String,

    @SerializedName("collectionName")
    @Expose
    var collectionName: String,

    @SerializedName("artworkUrl100")
    @Expose
    var artworkUrl100: String,

    @SerializedName("trackNumber")
    @Expose
    var trackNumber: Int?,

    @SerializedName("trackName")
    @Expose
    var trackName: String?,

    @SerializedName("releaseDate")
    @Expose
    var releaseDate: String?,

    @SerializedName("primaryGenreName")
    @Expose
    var primaryGenreName: String?,

    @SerializedName("artistViewUrl")
    @Expose
    var artistViewUrl: String
)