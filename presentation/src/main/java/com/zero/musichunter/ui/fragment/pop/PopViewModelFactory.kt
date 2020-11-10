package com.zero.musichunter.ui.fragment.pop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zero.musichunter.domain.repository.RepoRepository
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class PopViewModelFactory @Inject constructor (val repository: RepoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PopViewModel(repository) as T
    }
}