package com.zero.musichunter.ui.fragment.rock

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zero.musichunter.domain.usecases.GetRockListRepo

class RockMusicViewModelFactory(private val rockListRepo: GetRockListRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RockMusicViewModel(rockListRepo) as T
    }
}