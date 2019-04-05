package io.github.peerapongsam.heropedia.domain.usecase.base

import io.github.peerapongsam.heropedia.domain.executor.PostExecutionThread
import io.github.peerapongsam.heropedia.domain.executor.ThreadExecutor
import io.github.peerapongsam.heropedia.util.plusAssign
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber

abstract class FlowableUseCase<Result, in Params>(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : RxJavaUseCase(threadExecutor, postExecutionThread) {

    abstract fun create(params: Params? = null): Flowable<Result>

    fun execute(observer: DisposableSubscriber<Result> = EmptySubscriber(), params: Params? = null) {
        disposables += create(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler)
            .subscribeWith(observer)
    }

    inner class EmptySubscriber<Result> : DisposableSubscriber<Result>() {
        override fun onComplete() {}

        override fun onNext(result: Result) {}

        override fun onError(throwable: Throwable?) {}
    }
}
