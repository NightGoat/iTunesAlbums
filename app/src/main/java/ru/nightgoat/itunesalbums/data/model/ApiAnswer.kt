package ru.nightgoat.itunesalbums.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Класс данных возвращаемый API. Создается Retrofit парсингом JSON
 * @author NightGoat
 * @param resultCount Количество результатов в списке JSONа
 * @param albumResults Список объектов результата запроса @see [AlbumResult]
 */
data class ApiAnswer(
    @SerializedName("resultCount")
    @Expose
    var resultCount: Int,

    @SerializedName("results")
    @Expose
    var albumResults: List<AlbumResult>
)