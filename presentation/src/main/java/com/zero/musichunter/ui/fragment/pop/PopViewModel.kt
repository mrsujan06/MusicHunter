package com.zero.musichunter.ui.fragment.pop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.utils.TimberWrapper
import com.zero.musichunter.domain.model.Repo
import com.zero.musichunter.domain.repository.RepoRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PopViewModel @Inject constructor(val repository: RepoRepository) : ViewModel() {

    private val _popRepo = MutableLiveData<List<Repo>>()
    val popRepo: LiveData<List<Repo>>
        get() = _popRepo
    private val bag = CompositeDisposable()

    private val _popRepoCache = MutableLiveData<List<Repo>>()
    val popRepoCache: LiveData<List<Repo>>
        get() = _popRepoCache

    // Fetch pop music data from repository
    fun fetchPopFromNet() {
        bag.addAll(
            repository.getPopListFromNet()
                .subscribe({
                    _popRepo.value = it
                }, {
                    TimberWrapper.e { it }
                })
        )
    }

    fun fetchPopFromCache() {
        bag.addAll(
            repository
                .getCachePopListRepo()
                .subscribe({
                    _popRepoCache.value = it
                }, {
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        bag.clear()
    }

}