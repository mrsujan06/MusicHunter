package com.zero.musichunter.ui.fragment.pop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zero.musichunter.data.remote.NetworkMusicContainer
import com.zero.musichunter.data.repository.MusicRepo
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class PopViewModel @Inject constructor(val repository: MusicRepo) : ViewModel() {

    private val disposable = CompositeDisposable()
    private val _popMusicObservable = MutableLiveData<NetworkMusicContainer>()
    val popMusicObservable: LiveData<NetworkMusicContainer>
        get() = _popMusicObservable


    // Fetch pop music data from repository
//    fun popMusic() {
//        disposable.addAll(
//            repository.getPopMusic().subscribe({
//                _popMusicObservable.value = it
//            }, {
//                Timber.e("PopViewModel error : $it")
//            }
//            )
//        )
//    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}