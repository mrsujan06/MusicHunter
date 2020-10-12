package com.zero.musichunter.ui.fragment.rock

import androidx.lifecycle.ViewModel
import com.zero.musichunter.data.repository.MusicRepo
import io.reactivex.disposables.CompositeDisposable

class RockViewModel(val repository: MusicRepo) : ViewModel() {

    private val disposable = CompositeDisposable()
    val rockMusicObserver = repository.rockMusicObserver()

    fun getRockMusic() {
        disposable.addAll(
            repository.fetchRockMusic()
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}