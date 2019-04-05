package io.github.peerapongsam.heropedia.domain.usecase.base

import io.github.peerapongsam.heropedia.domain.executor.PostExecutionThread
import io.github.peerapongsam.heropedia.domain.executor.ThreadExecutor
import io.github.peerapongsam.heropedia.util.plusAssign
import io.reactivex.Completable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

abstract class CompletableUseCase<Params>(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : RxJavaUseCase(threadExecutor, postExecutionThread) {

    abstract fun create(params: Params? = null): Completable

    fun execute(observer: DisposableCompletableObserver = EmptyCompletableObserver(), params: Params? = null) {
        disposables += create(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler)
            .subscribeWith(observer)
    }

    inner class EmptyCompletableObserver : DisposableCompletableObserver() {
        override fun onComplete() {}

        override fun onError(throwable: Throwable) {}
    }
}
