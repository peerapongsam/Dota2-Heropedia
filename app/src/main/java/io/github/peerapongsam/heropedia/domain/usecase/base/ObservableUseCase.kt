package io.github.peerapongsam.heropedia.domain.usecase.base

import io.github.peerapongsam.heropedia.domain.executor.PostExecutionThread
import io.github.peerapongsam.heropedia.domain.executor.ThreadExecutor
import io.github.peerapongsam.heropedia.util.plusAssign
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class ObservableUseCase<Result, Params>(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : RxJavaUseCase(threadExecutor, postExecutionThread) {

    abstract fun create(params: Params? = null): Observable<Result>

    open fun execute(observer: DisposableObserver<Result> = EmptyObserver(), params: Params? = null) {
        disposables += create(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler)
            .subscribeWith(observer)
    }

    inner class EmptyObserver<Result> : DisposableObserver<Result>() {
        override fun onComplete() {}

        override fun onNext(result: Result) {}

        override fun onError(throwable: Throwable) {}
    }
}
