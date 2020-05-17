package ru.nightgoat.itunesalbums.presentation.search

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import ru.nightgoat.itunesalbums.presentation.base.BaseViewModel
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * ViewModel фрагмента поиска альбомов
 * @author NightGoat
 */
class SearchViewModel : BaseViewModel(), LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(){
            toastLiveData.value = null
    }
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
                    toastLiveData.value = null
                    isProgressBarVisibleLiveData.value = true
                }
                .doFinally {
                    isProgressBarVisibleLiveData.value = false
                }
                .subscribe({
                    resultListLiveData.value = it
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
