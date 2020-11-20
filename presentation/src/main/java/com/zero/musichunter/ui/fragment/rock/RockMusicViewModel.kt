package com.zero.musichunter.ui.fragment.rock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.data.utils.TimberWrapper
import com.zero.musichunter.domain.model.Repo
import com.zero.musichunter.domain.usecases.GetRockListRepo
import com.zero.musichunter.ui.fragment.base.viewmodel.BaseViewModel

class RockMusicViewModel(private val rockListRepo: GetRockListRepo) : BaseViewModel() {

    private val _rockRepo = MutableLiveData<List<Repo>>()
    val rockRepo: LiveData<List<Repo>>
        get() = _rockRepo

    fun loadRockRepo() {
        composite.addAll(
            rockListRepo.execute()
                .subscribe({
                    _rockRepo.value = it
                }, {
                    TimberWrapper.e { it }
                })
        )
    }

}