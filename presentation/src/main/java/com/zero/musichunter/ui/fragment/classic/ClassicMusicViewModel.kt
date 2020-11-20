package com.zero.musichunter.ui.fragment.classic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.data.utils.TimberWrapper
import com.zero.musichunter.domain.model.Repo
import com.zero.musichunter.domain.usecases.GetClassicListRepo
import com.zero.musichunter.ui.fragment.base.viewmodel.BaseViewModel

class ClassicMusicViewModel(private val getClassicListRepo: GetClassicListRepo) :
    BaseViewModel() {

    private val _classicRepo = MutableLiveData<List<Repo>>()
    val classicRepo: LiveData<List<Repo>>
        get() = _classicRepo

    fun loadClassicRepo() {
        composite.addAll(
            getClassicListRepo.execute()
                .subscribe({
                    _classicRepo.value = it
                }, {
                    TimberWrapper.e { it }
                })
        )
    }
}
