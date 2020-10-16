package com.zero.musichunter.ui.fragment.rock

import androidx.lifecycle.ViewModel
import com.zero.musichunter.data.repository.MusicRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException

class RockViewModel(val repository: MusicRepo) : ViewModel() {

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    val playlist = repository.rockMusicObservable()

    // Fetch pop music data from repository
    fun rockMusic() = coroutineScope.launch {
        try {
            repository.getRockMusic()
        } catch (networkError: IOException) {
            Timber.e("network error : $networkError")
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}