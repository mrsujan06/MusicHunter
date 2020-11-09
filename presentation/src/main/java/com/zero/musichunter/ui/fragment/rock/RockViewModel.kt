package com.zero.musichunter.ui.fragment.rock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.utils.TimberWrapper
import com.zero.musichunter.domain.model.Repo
import com.zero.musichunter.domain.repository.RepoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.io.IOException

class RockViewModel(val repository: RepoRepository) : ViewModel() {

    private val _rockMusicList = MutableLiveData<List<Repo>>()
    val rockMusicList: LiveData<List<Repo>>
        get() = _rockMusicList
    val bag = CompositeDisposable()

    // Fetch pop music data from repository
    fun rockMusic() {
        try {
            bag.addAll(
                repository.getRockListRepo()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        _rockMusicList.value = it
                        TimberWrapper.d { "fetched rock music ---" }
                    }, {
                        TimberWrapper.e { it }
                    })
            )
        } catch (networkError: IOException) {
            Timber.e("network error : $networkError")
        }
    }

    override fun onCleared() {
        super.onCleared()
        bag.clear()
    }
}