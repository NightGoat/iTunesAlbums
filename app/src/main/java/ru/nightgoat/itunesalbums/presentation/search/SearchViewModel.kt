package ru.nightgoat.itunesalbums.presentation.search

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.nightgoat.itunesalbums.presentation.base.BaseViewModel

/**
 * ViewModel фрагмента поиска альбомов
 * @author NightGoat
 */
class SearchViewModel : BaseViewModel() {

    /**
     * Метод поиска альбомов.
     * @param name название альбома
     */
    fun searchAlbum(name: String) {
        compositeDisposable.add(
            repository.getAlbumsList(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    it.results.sortedBy { results ->
                        results.collectionName
                    }
                }
                .doOnSubscribe {
                    isProgressBarVisibleLiveData.value = true
                }
                .doFinally {
                    isProgressBarVisibleLiveData.value = false
                }
                .subscribe({
                    resultListLiveData.value = it
                }, {
                    it.printStackTrace()
                    toastLiveData.value = it.message
                })

        )
    }
}
