package com.zero.musichunter.ui.fragment.pop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zero.musichunter.data.repository.MusicRepo
import javax.inject.Inject

class PopViewModelFactory @Inject constructor(val repository: MusicRepo) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PopViewModel(repository) as T
    }
}