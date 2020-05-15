package ru.nightgoat.itunesalbums.presentation.album

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.nightgoat.itunesalbums.presentation.base.BaseViewModel

/**
 * ViewModel для [AlbumFragment]. Делает запрос описания альбома.
 * @see [BaseViewModel]
 * @author NightGoat
 */
class AlbumViewModel : BaseViewModel() {

    companion object {
        val TAG = AlbumViewModel::class.java.name
    }

    /**
     * Метод получения описания альбома. CompositeDisposable.dispose() и LiveData в BaseViewModel
     * @param albumId id альбома
     * @author NightGoat
     */
    fun getAlbumInfo(albumId: Long) {
        compositeDisposable.add(
            repository.getAlbum(albumId)
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    it.results
                }
                .doOnSubscribe {
                    isProgressBarVisibleLiveData.value = true
                    Log.d(TAG, "doOnSubscribe: ${isProgressBarVisibleLiveData.value}")
                }
                .doFinally {
                    isProgressBarVisibleLiveData.value = false
                    Log.d(TAG, "doFinally: ${isProgressBarVisibleLiveData.value}")
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
