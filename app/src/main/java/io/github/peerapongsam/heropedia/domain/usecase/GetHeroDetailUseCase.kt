package io.github.peerapongsam.heropedia.domain.usecase

import io.github.peerapongsam.heropedia.data.HeropediaService
import io.github.peerapongsam.heropedia.data.Service
import io.github.peerapongsam.heropedia.domain.executor.PostExecutionThread
import io.github.peerapongsam.heropedia.domain.executor.ThreadExecutor
import io.github.peerapongsam.heropedia.domain.usecase.GetHeroDetailUseCase.Params
import io.github.peerapongsam.heropedia.domain.usecase.base.ObservableUseCase
import io.github.peerapongsam.heropedia.model.HeroDetail
import io.reactivex.Observable

class GetHeroDetailUseCase(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<HeroDetail, Params>(threadExecutor, postExecutionThread) {

    override fun create(params: Params?): Observable<HeroDetail> {
        return Service.defaultService.create(HeropediaService::class.java).getHeroDetail(name = params!!.id)
    }

    data class Params(val id: String)
}
