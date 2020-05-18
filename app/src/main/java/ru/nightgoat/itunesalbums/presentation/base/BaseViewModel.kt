package ru.nightgoat.itunesalbums.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import ru.nightgoat.itunesalbums.data.model.AlbumResult
import ru.nightgoat.itunesalbums.domain.Repository
import ru.nightgoat.itunesalbums.domain.RepositoryProvider

/**
 * Базовая ViewModel.
 * @author NightGoat
 */
abstract class BaseViewModel : ViewModel() {

    var repository: Repository = RepositoryProvider.provideItunesApiRepository()

    val albumResultListLiveData: MutableLiveData<List<AlbumResult>> by lazy {
        MutableLiveData<List<AlbumResult>>()
    }

    val toastLiveData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val isProgressBarVisibleLiveData: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}