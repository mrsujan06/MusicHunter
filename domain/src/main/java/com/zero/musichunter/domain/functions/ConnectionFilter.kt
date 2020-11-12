package com.zero.musichunter.domain.functions

import com.zero.musichunter.domain.exception.NoConnectedException
import io.reactivex.rxjava3.functions.Predicate


class ConnectionFilter : Predicate<Boolean> {
    @Throws(Exception::class)
    override fun test(isConnected: Boolean): Boolean {
        if (!isConnected) {
            throw NoConnectedException
        }
        return true
    }
}