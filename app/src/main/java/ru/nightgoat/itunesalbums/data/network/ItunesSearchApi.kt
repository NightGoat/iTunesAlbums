package ru.nightgoat.itunesalbums.data.network

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.nightgoat.itunesalbums.data.model.ApiAnswer
import ru.nightgoat.itunesalbums.utils.BASE_URL

interface ItunesSearchApi {

    @GET("search?media=music&entity=album")
    fun getAlbumList(
        @Query("term") album : String
    ) : Single<ApiAnswer>

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