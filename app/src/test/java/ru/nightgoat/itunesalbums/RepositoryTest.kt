package ru.nightgoat.itunesalbums

import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import ru.nightgoat.itunesalbums.data.model.ApiAnswer
import ru.nightgoat.itunesalbums.data.model.AlbumResult
import ru.nightgoat.itunesalbums.data.network.ItunesSearchApi
import ru.nightgoat.itunesalbums.domain.RepositoryProvider

/**
 * Простой тест который проверяет что репозиторий мапит Answer в сортированный по алфавиту List<AlbumResult>
 * @author NightGoat
 */
class RepositoryTest {

    private val service = Mockito.mock(ItunesSearchApi::class.java)
    private val repository = RepositoryProvider.provideTestRepository(service)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(service.getAlbumList("Highway to hell"))
            .thenReturn(Single.just(getFromApiAlbumModels()))
    }

    @Test
    fun getAlbums_repository_returns_mapped_and_sorted_list(){
        val testObservable = repository.getAlbumsList("Highway to hell").test()
        testObservable.awaitTerminalEvent()
        testObservable.assertValue(getAlbumList())
        testObservable.assertNever(getWrongAlbumList())
    }

    private fun getFromApiAlbumModels() : ApiAnswer {
        return ApiAnswer(2, listOf(albumB(), albumA()))
    }

    private fun getAlbumList() : List<AlbumResult> {
        return listOf(albumA(), albumB())
    }

    private fun getWrongAlbumList() : List<AlbumResult> {
        return listOf(albumB(), albumA())
    }

    private fun albumA() = AlbumResult(
        "collection",
        "album",
        2,
        "AC/DC",
        "A",
        "https:\\itunes.itunes",
        null,
        null,
        null,
        null,
        "https:\\itunes.itunes"
    )

    private fun albumB() = AlbumResult(
    "collection",
    "album",
    1,
    "Highway To Hell",
    "B",
    "https:\\itunes.itunes",
    null,
    null,
    null,
    null,
    "https:\\itunes.itunes"
    )
}