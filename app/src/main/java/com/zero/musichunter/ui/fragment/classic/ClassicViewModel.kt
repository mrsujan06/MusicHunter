package com.zero.musichunter.ui.fragment.classic

import androidx.lifecycle.ViewModel
import com.zero.musichunter.data.repository.MusicRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class ClassicViewModel @Inject constructor(var repository: MusicRepo) : ViewModel() {

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    val playlist = repository.classicMusicObservable()

    fun fetchClassicMusic() = coroutineScope.launch {
        try {
            repository.getClassicMusic()
        } catch (networkError: IOException) {
            Timber.e("network error : $networkError")
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
