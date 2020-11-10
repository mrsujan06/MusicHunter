package com.zero.musichunter.ui.fragment.classic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.utils.TimberWrapper
import com.zero.musichunter.domain.model.Repo
import com.zero.musichunter.domain.repository.RepoRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ClassicViewModel @Inject constructor(var repository: RepoRepository) : ViewModel() {

    private val bag = CompositeDisposable()

    private val _classicRepoNet = MutableLiveData<List<Repo>>()
    val classicRepoNet: LiveData<List<Repo>>
        get() = _classicRepoNet

    private val _classicRepoCache = MutableLiveData<List<Repo>>()
    val classicRepoCache: LiveData<List<Repo>>
        get() = _classicRepoCache

    fun fetchClassicFromNet() {
        bag.addAll(
            repository.getClassicListFromNet()
                .subscribe({
                    _classicRepoNet.value = it
                }, {
                    TimberWrapper.e { it }
                })
        )
    }

    fun fetchClassicFromCache() {
        bag.addAll(
            repository
                .getCacheClassicListRepo()
                .subscribe({
                    _classicRepoCache.value = it
                }, {
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        bag.clear()
    }
}
