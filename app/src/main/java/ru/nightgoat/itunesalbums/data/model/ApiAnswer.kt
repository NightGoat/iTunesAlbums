package ru.nightgoat.itunesalbums.data.model

data class ApiAnswer(
    var resultCount: Int,
    var results: List<Results>
)