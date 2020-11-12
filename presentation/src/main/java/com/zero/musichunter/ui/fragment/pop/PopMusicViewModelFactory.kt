package com.zero.musichunter.ui.fragment.pop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zero.musichunter.domain.usecases.GetPopListRepo
import javax.inject.Inject

class PopMusicViewModelFactory @Inject constructor (val popListRepo: GetPopListRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PopMusicViewModel(popListRepo) as T
    }
}