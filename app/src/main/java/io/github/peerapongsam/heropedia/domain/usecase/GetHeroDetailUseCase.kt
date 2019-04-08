package io.github.peerapongsam.heropedia.domain.usecase

import io.github.peerapongsam.heropedia.data.repo.HeroRepository
import io.github.peerapongsam.heropedia.domain.executor.PostExecutionThread
import io.github.peerapongsam.heropedia.domain.executor.ThreadExecutor
import io.github.peerapongsam.heropedia.domain.usecase.GetHeroDetailUseCase.Params
import io.github.peerapongsam.heropedia.domain.usecase.base.ObservableUseCase
import io.github.peerapongsam.heropedia.model.HeroDetail
import io.reactivex.Observable

class GetHeroDetailUseCase(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    private val heroRepository: HeroRepository
) : ObservableUseCase<HeroDetail, Params>(threadExecutor, postExecutionThread) {

    override fun create(params: Params?): Observable<HeroDetail> {
        return heroRepository.getHeroDetail(params!!.id)
    }

    data class Params(val id: String)
}
