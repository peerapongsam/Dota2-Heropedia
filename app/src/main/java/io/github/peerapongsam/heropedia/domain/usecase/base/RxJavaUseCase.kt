package io.github.peerapongsam.heropedia.domain.usecase.base

import io.github.peerapongsam.heropedia.domain.executor.PostExecutionThread
import io.github.peerapongsam.heropedia.domain.executor.ThreadExecutor
import io.reactivex.disposables.CompositeDisposable

abstract class RxJavaUseCase(
    val threadExecutor: ThreadExecutor,
    val postExecutionThread: PostExecutionThread
) {

    protected val disposables = CompositeDisposable()

    open fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }
}
