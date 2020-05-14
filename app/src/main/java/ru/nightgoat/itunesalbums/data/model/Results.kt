package ru.nightgoat.itunesalbums.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Results (
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
    var artworkUrl100: String
)