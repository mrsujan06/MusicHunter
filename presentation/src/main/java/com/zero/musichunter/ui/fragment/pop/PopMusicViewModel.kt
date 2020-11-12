package com.zero.musichunter.ui.fragment.pop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.utils.TimberWrapper
import com.zero.musichunter.domain.model.Repo
import com.zero.musichunter.domain.usecases.GetPopListRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class PopMusicViewModel(private val popListRepo: GetPopListRepo) : ViewModel() {

    private val composite = CompositeDisposable()

    private val _popRepo = MutableLiveData<List<Repo>>()
    val popRepo: LiveData<List<Repo>>
        get() = _popRepo

    fun loadPopRepo() {
        composite.addAll(
            popListRepo.execute().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _popRepo.value = it
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