package com.zero.musichunter.domain.usecases

import com.zero.musichunter.domain.exception.NoConnectedException
import com.zero.musichunter.domain.functions.StatementSingle
import com.zero.musichunter.domain.model.Repo
import com.zero.musichunter.domain.repository.RockRepository
import com.zero.musichunter.domain.usecases.base.Logger
import com.zero.musichunter.domain.usecases.base.SingleUseCase
import com.zero.musichunter.domain.usecases.base.UseCaseScheduler
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * This class is an implementation of [SingleUseCase] that represents a use case for
 * retrieving a collection of all rock[Repo].
 */
class GetRockListRepo @Inject constructor(
    private val rockRepository: RockRepository,
    useCaseScheduler: UseCaseScheduler, logger: Logger
) : SingleUseCase<List<Repo>, Void>(useCaseScheduler, logger) {
    override fun build(param: Void?): Single<List<Repo>> {

        val getCacheRockListRepo = rockRepository.getCacheRockListRepo()

        val cacheSingle = getCacheRockListRepo.map {
            if (it.isEmpty()) throw NoConnectedException else it
        }
        val netSingle = rockRepository.getRockListFromNet()
            .flatMap {
                rockRepository.saveRockListRepo(it).andThen(getCacheRockListRepo)
            }

        return StatementSingle.ifThen(rockRepository.isConnected, netSingle, cacheSingle)
    }

}