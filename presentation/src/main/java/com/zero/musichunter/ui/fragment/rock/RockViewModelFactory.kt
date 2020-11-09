package com.zero.musichunter.ui.fragment.rock

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zero.musichunter.domain.repository.RepoRepository

class RockViewModelFactory(val repository: RepoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RockViewModel(repository) as T
    }
}