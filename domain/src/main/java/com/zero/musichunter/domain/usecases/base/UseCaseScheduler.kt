package com.zero.musichunter.domain.usecases.base

import io.reactivex.rxjava3.core.Scheduler

data class UseCaseScheduler(val run: Scheduler, val post: Scheduler)
