package ru.nightgoat.itunesalbums.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Result (

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
    var primaryGenreName: String?
)