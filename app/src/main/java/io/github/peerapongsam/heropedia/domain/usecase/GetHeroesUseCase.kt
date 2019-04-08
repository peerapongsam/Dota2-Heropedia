package io.github.peerapongsam.heropedia.domain.usecase

import io.github.peerapongsam.heropedia.data.repo.HeroRepository
import io.github.peerapongsam.heropedia.domain.executor.PostExecutionThread
import io.github.peerapongsam.heropedia.domain.executor.ThreadExecutor
import io.github.peerapongsam.heropedia.domain.usecase.base.ObservableUseCase
import io.github.peerapongsam.heropedia.model.Hero
import io.reactivex.Observable

open class GetHeroesUseCase(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    private val heroRepository: HeroRepository
) : ObservableUseCase<List<Hero>, Nothing>(threadExecutor, postExecutionThread) {

    override fun create(params: Nothing?): Observable<List<Hero>> {
        return heroRepository.getHeroes()
    }
}
