package com.zero.musichunter.ui.fragment.pop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.data.utils.TimberWrapper
import com.zero.musichunter.domain.model.Repo
import com.zero.musichunter.domain.usecases.GetPopListRepo
import com.zero.musichunter.ui.fragment.base.viewmodel.BaseViewModel

class PopMusicViewModel(private val popListRepo: GetPopListRepo) : BaseViewModel() {

    private val _popRepo = MutableLiveData<List<Repo>>()
    val popRepo: LiveData<List<Repo>>
        get() = _popRepo

    fun loadPopRepo() {
        composite.addAll(
            popListRepo.execute()
                .subscribe({
                    _popRepo.postValue(it)
                }, {
                    TimberWrapper.e { it }
                })
        )
    }

}