package ru.nightgoat.itunesalbums.presentation.album

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import retrofit2.HttpException
import ru.nightgoat.itunesalbums.presentation.base.BaseViewModel
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * ViewModel для [AlbumFragment]. Делает запрос описания альбома.
 * @see [BaseViewModel]
 * @author NightGoat
 */
class AlbumViewModel : BaseViewModel(), LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        toastLiveData.value = null
    }

    /**
     * Метод получения описания альбома. CompositeDisposable.dispose() и LiveData в BaseViewModel
     * @param albumId id альбома
     * @author NightGoat
     */
    fun getAlbumInfo(albumId: Long) {
        if (albumId != -1L) {
            compositeDisposable.add(
                repository.getAlbum(albumId)
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        isProgressBarVisibleLiveData.value = true
                    }
                    .doFinally {
                        isProgressBarVisibleLiveData.value = false
                    }
                    .subscribe({
                        albumResultListLiveData.value = it
                    }, {
                        when (it) {
                            is UnknownHostException -> {
                                toastLiveData.value = "Нет интернета"
                            }
                            is SocketTimeoutException -> {
                                toastLiveData.value = "Нет интернета"
                            }
                            is HttpException -> {
                                toastLiveData.value = "${it.code()}: ${it.message()}"
                            }
                            else -> toastLiveData.value = it.message
                        }
                        it.printStackTrace()
                    })
            )
        }
    }
}
