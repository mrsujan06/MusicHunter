package com.zero.musichunter.ui.fragment.pop

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
import javax.inject.Inject

class PopViewModel @Inject constructor(val repository: RepoRepository) : ViewModel() {

    private val _popMusicList = MutableLiveData<List<Repo>>()
    val popMusicList: LiveData<List<Repo>>
        get() = _popMusicList
    val bag = CompositeDisposable()

    // Fetch pop music data from repository
    fun popMusic() {
        try {
            bag.addAll(
                repository.getPopListRepo()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        TimberWrapper.d { "PopMusic fetched" }
                        _popMusicList.value = it
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