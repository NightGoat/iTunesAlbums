package ru.nightgoat.itunesalbums.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import ru.nightgoat.itunesalbums.data.model.Result

abstract class BaseViewModel : ViewModel() {
    val resultListLiveData : MutableLiveData<List<Result>> by lazy {
        MutableLiveData<List<Result>>()
    }
    val toastLiveData : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val isProgressBarVisibleLiveData : MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}