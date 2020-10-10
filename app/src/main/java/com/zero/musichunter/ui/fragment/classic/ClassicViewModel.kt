package com.zero.musichunter.ui.fragment.classic

import androidx.lifecycle.ViewModel
import com.zero.musichunter.data.repository.MusicRepo
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class ClassicViewModel @Inject constructor(var repository: MusicRepo) : ViewModel() {

    private val disposable = CompositeDisposable()

    val playlists = repository.classicMusicObserver()

    fun getClassicMusicData() =
        try {
            disposable.add(repository.fetchClassicMusic())
        } catch (networkError: IOException) {
            Timber.e("ClassicViewModel error :$networkError")
        }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}




