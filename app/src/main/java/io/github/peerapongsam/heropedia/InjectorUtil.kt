package io.github.peerapongsam.heropedia

import io.github.peerapongsam.heropedia.data.executor.JobExecutor
import io.github.peerapongsam.heropedia.domain.executor.PostExecutionThread
import io.github.peerapongsam.heropedia.domain.executor.ThreadExecutor
import io.github.peerapongsam.heropedia.domain.usecase.GetHeroesUseCase
import io.github.peerapongsam.heropedia.ui.UiThread
import io.github.peerapongsam.heropedia.ui.heroes.HeroesViewModelFactory

object InjectorUtil {

    private fun provideThreadExecutor(): ThreadExecutor {
        return JobExecutor()
    }

    private fun providePostExecutionThread(): PostExecutionThread {
        return UiThread()
    }

    private fun provideGetHeroesUseCase(): GetHeroesUseCase {
        return GetHeroesUseCase(provideThreadExecutor(), providePostExecutionThread())
    }

    fun provideHeroesViewModelFactory(): HeroesViewModelFactory {
        return HeroesViewModelFactory(provideGetHeroesUseCase())
    }
}
