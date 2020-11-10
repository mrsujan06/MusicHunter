package com.zero.musichunter.ui.fragment.classic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zero.musichunter.domain.repository.RepoRepository
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ClassicViewModelFactory @Inject constructor(val repository: RepoRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ClassicViewModel(repository) as T
    }
}