package io.github.peerapongsam.heropedia.domain.usecase

import io.github.peerapongsam.heropedia.data.ApiService
import io.github.peerapongsam.heropedia.data.Service
import io.github.peerapongsam.heropedia.domain.executor.PostExecutionThread
import io.github.peerapongsam.heropedia.domain.executor.ThreadExecutor
import io.github.peerapongsam.heropedia.domain.usecase.base.ObservableUseCase
import io.github.peerapongsam.heropedia.model.HeropediaData
import io.reactivex.Observable

class GetHeroesUseCase(threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread) :
    ObservableUseCase<HeropediaData, Nothing>(threadExecutor, postExecutionThread) {

    override fun create(params: Nothing?): Observable<HeropediaData> {
        return Service.defaultService.create(ApiService::class.java).getHeropediaData()
    }
}
