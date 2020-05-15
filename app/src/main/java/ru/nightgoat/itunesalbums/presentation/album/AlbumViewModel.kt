package ru.nightgoat.itunesalbums.presentation.album

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.nightgoat.itunesalbums.domain.Repository
import ru.nightgoat.itunesalbums.domain.RepositoryProvider
import ru.nightgoat.itunesalbums.presentation.base.BaseViewModel

class AlbumViewModel : BaseViewModel() {

    var repository: Repository = RepositoryProvider.provideRepository()

    fun getAlbumInfo(albumId: Long) {
        compositeDisposable.add(
            repository.getAlbum(albumId)
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    it.results
                }
                .doOnSubscribe {
                    isProgressBarVisibleLiveData.value = true
                }
                .subscribe({
                    resultListLiveData.value = it
                    isProgressBarVisibleLiveData.value = false
                }, {
                    toastLiveData.value = it.message
                    isProgressBarVisibleLiveData.value = false
                })
        )
    }
}
