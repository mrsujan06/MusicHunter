package com.zero.musichunter.ui.fragment.classic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zero.musichunter.domain.model.Repo
import com.zero.musichunter.domain.repository.RepoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class ClassicViewModel @Inject constructor(var repository: RepoRepository) : ViewModel() {

    private val bag = CompositeDisposable()
    private val _classicRepo = MutableLiveData<List<Repo>>()
    val classicRepo: LiveData<List<Repo>>
        get() = _classicRepo

    fun fetchClassicMusic() {
        bag.addAll(
            repository.getClassicListRepo()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _classicRepo.value = it
                }, {
                    Timber.d("Error while getting data from viewModel")
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        bag.clear()
    }
}
