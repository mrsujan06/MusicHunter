package com.zero.musichunter.ui.fragment.rock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.utils.TimberWrapper
import com.zero.musichunter.domain.model.Repo
import com.zero.musichunter.domain.usecases.GetRockListRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class RockMusicViewModel(private val rockListRepo: GetRockListRepo) : ViewModel() {

    private val composite = CompositeDisposable()
    private val _rockRepo = MutableLiveData<List<Repo>>()
    val rockRepo: LiveData<List<Repo>>
        get() = _rockRepo

    fun loadRockRepo() {
        composite.addAll(
            rockListRepo.execute().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _rockRepo.value = it
                }, {
                    TimberWrapper.e { it }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        composite.clear()
    }
}