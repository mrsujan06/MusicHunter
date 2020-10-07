package com.zero.musichunter.ui.fragment.classic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zero.musichunter.data.model.MusicResponse
import com.zero.musichunter.data.repository.MusicRepo
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class ClassicViewModel @Inject constructor(var repository: MusicRepo) : ViewModel() {

    private val disposable = CompositeDisposable()

    private val _classicMusicObservable = MutableLiveData<MusicResponse>()
    val classicMusicObservable: LiveData<MusicResponse>
        get() = _classicMusicObservable

    fun fetchClassicMusic() {
        disposable.addAll(
            repository.getClassicMusic().subscribe({
                _classicMusicObservable.value = it
            }, {
                Timber.e("ClassicViewModel error : $it")
            }
            )
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
