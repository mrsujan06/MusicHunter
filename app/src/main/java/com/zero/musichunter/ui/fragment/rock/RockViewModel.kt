package com.zero.musichunter.ui.fragment.rock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zero.musichunter.data.model.MusicResponse
import com.zero.musichunter.data.repository.MusicRepo
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class RockViewModel(val repository: MusicRepo) : ViewModel() {

    private val disposable = CompositeDisposable()
    private val _rockMusicObservable = MutableLiveData<MusicResponse>()

    val rockMusicObservable: LiveData<MusicResponse>
        get() = _rockMusicObservable

    fun getRockMusic() {
        disposable.addAll(
            repository.getRockMusic().subscribe({
                _rockMusicObservable.value = it
            }, {
                Timber.e("RockViewModel error: $it")
            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}