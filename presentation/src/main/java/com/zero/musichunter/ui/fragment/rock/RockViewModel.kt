package com.zero.musichunter.ui.fragment.rock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.utils.TimberWrapper
import com.zero.musichunter.domain.model.Repo
import com.zero.musichunter.domain.repository.RepoRepository
import io.reactivex.disposables.CompositeDisposable

class RockViewModel(val repository: RepoRepository) : ViewModel() {

    private val _rockRepo = MutableLiveData<List<Repo>>()
    val rockRepo: LiveData<List<Repo>>
        get() = _rockRepo

    private val _rockRepoCache = MutableLiveData<List<Repo>>()
    val rockRepoCache: LiveData<List<Repo>>
        get() = _rockRepoCache

    private val bag = CompositeDisposable()

    // Fetch pop music data from repository
    fun fetchRockFromNet() {
        bag.addAll(
            repository.getRockListFromNet()
                .subscribe({
                    _rockRepo.value = it
                    TimberWrapper.d { "fetched rock music ---" }
                }, {
                    TimberWrapper.e { it }
                })
        )
    }

    fun fetchRockFromCache() {
        bag.addAll(
            repository
                .getCacheRockListRepo()
                .subscribe({
                    _rockRepoCache.value = it
                }, {
                    TimberWrapper.e { it }
                })
        )
    }


    override fun onCleared() {
        super.onCleared()
        bag.clear()
    }
}