package io.github.peerapongsam.heropedia.data.executor

import io.github.peerapongsam.heropedia.domain.executor.ThreadExecutor
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class JobExecutor : ThreadExecutor {

    companion object {
        const val THREAD_POOL = 3
    }

    private val threadPoolExecutor: Executor = Executors.newFixedThreadPool(THREAD_POOL)

    override fun execute(runnable: Runnable?) {
        threadPoolExecutor.execute(runnable)
    }
}
