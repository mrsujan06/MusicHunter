package com.zero.musichunter.ui.fragment.classic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.utils.TimberWrapper
import com.zero.musichunter.domain.model.Repo
import com.zero.musichunter.domain.usecases.GetClassicListRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ClassicMusicViewModel(private val getClassicListRepo: GetClassicListRepo) :
    ViewModel() {

    private val composite = CompositeDisposable()
    private val _classicRepo = MutableLiveData<List<Repo>>()
    val classicRepo: LiveData<List<Repo>>
        get() = _classicRepo

    fun loadClassicRepo() {
        composite.addAll(
            getClassicListRepo.execute().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _classicRepo.value = it
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
