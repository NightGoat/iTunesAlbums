package ru.nightgoat.itunesalbums.presentation.search

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.nightgoat.itunesalbums.data.model.Results
import ru.nightgoat.itunesalbums.domain.Repository
import ru.nightgoat.itunesalbums.domain.RepositoryProvider
import ru.nightgoat.itunesalbums.presentation.base.BaseViewModel

class SearchViewModel : BaseViewModel() {

    val resultListLiveData : MutableLiveData<List<Results>> by lazy {
        MutableLiveData<List<Results>>()
    }
    val toastLiveData : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val isProgressBarVisibleLiveData : MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    var repository: Repository = RepositoryProvider.provideRepository()

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
                .subscribe({
                    resultListLiveData.value = it
                    isProgressBarVisibleLiveData.value = false
                }, {
                    it.printStackTrace()
                    toastLiveData.value = it.message
                    isProgressBarVisibleLiveData.value = false
                })

        )
    }
}
