package com.zero.musichunter.ui.fragment.classic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zero.musichunter.data.repository.MusicRepo
import javax.inject.Inject

class ClassicViewModelFactory @Inject constructor(val repository: MusicRepo) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ClassicViewModel(repository) as T
    }
}