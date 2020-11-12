package com.zero.musichunter.ui.fragment.classic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zero.musichunter.domain.usecases.GetClassicListRepo

@Suppress("UNCHECKED_CAST")
class ClassicMusicViewModelFactory (private val getClassicListRepo: GetClassicListRepo) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ClassicMusicViewModel(getClassicListRepo) as T
    }
}