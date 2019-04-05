package io.github.peerapongsam.heropedia.domain.usecase.base

import io.github.peerapongsam.heropedia.domain.executor.PostExecutionThread
import io.github.peerapongsam.heropedia.domain.executor.ThreadExecutor
import io.github.peerapongsam.heropedia.util.plusAssign
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<Result, Params>(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : RxJavaUseCase(threadExecutor, postExecutionThread) {

    abstract fun create(params: Params? = null): Single<Result>

    fun execute(observer: DisposableSingleObserver<Result> = EmptySingleObserver(), params: Params? = null) {
        disposables += create(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler)
            .subscribeWith(observer)
    }

    inner class EmptySingleObserver<Result> : DisposableSingleObserver<Result>() {
        override fun onSuccess(result: Result) {
        }

        override fun onError(throwable: Throwable) {
        }
    }
}
