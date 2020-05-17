package ru.nightgoat.itunesalbums.data.model

/**
 * Класс данных возвращаемый API. Создается Retrofit парсингом JSON
 * @author NightGoat
 * @param resultCount Количество результатов в списке JSONа
 * @param results Список объектов результата запроса @see [Result]
 */
data class ApiAnswer(
    var resultCount: Int,
    var results: List<Result>
)