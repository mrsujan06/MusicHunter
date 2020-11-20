package com.zero.musichunter.ui.fragment.base.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    protected val composite = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        composite.clear()
    }
}