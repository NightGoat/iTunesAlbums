package ru.nightgoat.itunesalbums.domain

import ru.nightgoat.itunesalbums.data.RepositoryImpl
import ru.nightgoat.itunesalbums.data.network.ItunesSearchApi

object RepositoryProvider {
    fun provideRepository(): Repository {
        return RepositoryImpl(ItunesSearchApi.create())
    }
}