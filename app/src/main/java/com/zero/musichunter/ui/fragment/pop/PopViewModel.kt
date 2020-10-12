package com.zero.musichunter.ui.fragment.pop

import androidx.lifecycle.ViewModel
import com.zero.musichunter.data.repository.MusicRepo
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PopViewModel @Inject constructor(val repository: MusicRepo) : ViewModel() {

    private val disposable = CompositeDisposable()
    val popMusicObserver = repository.popMusicObserver()

    // Fetch pop music data from repository
    fun popMusic() {
        disposable.addAll(
            repository.fetchPopMusic()
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}