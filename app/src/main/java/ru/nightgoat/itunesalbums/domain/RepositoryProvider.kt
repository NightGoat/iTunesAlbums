package ru.nightgoat.itunesalbums.domain

import ru.nightgoat.itunesalbums.data.RepositoryImpl
import ru.nightgoat.itunesalbums.data.network.ItunesSearchApi

/**
 * Провайдер создающий Api и возвращающий синглтон реализации репозитория
 * @author NightGoat
 */
object RepositoryProvider {
    fun provideRepository(): Repository {
        return RepositoryImpl(ItunesSearchApi.create())
    }
}