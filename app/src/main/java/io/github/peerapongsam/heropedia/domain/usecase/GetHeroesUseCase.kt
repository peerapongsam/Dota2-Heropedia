package io.github.peerapongsam.heropedia.domain.usecase

import io.github.peerapongsam.heropedia.data.HeropediaService
import io.github.peerapongsam.heropedia.data.Service
import io.github.peerapongsam.heropedia.domain.executor.PostExecutionThread
import io.github.peerapongsam.heropedia.domain.executor.ThreadExecutor
import io.github.peerapongsam.heropedia.domain.usecase.base.ObservableUseCase
import io.github.peerapongsam.heropedia.model.Hero
import io.reactivex.Observable

class GetHeroesUseCase(threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread) :
    ObservableUseCase<List<Hero>, Nothing>(threadExecutor, postExecutionThread) {

    override fun create(params: Nothing?): Observable<List<Hero>> {
        return Service.defaultService.create(HeropediaService::class.java).getHeroes()
    }
}
