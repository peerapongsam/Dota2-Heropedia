package io.github.peerapongsam.heropedia

import io.github.peerapongsam.heropedia.data.executor.JobExecutor
import io.github.peerapongsam.heropedia.data.remote.HeropediaService
import io.github.peerapongsam.heropedia.data.remote.ServiceUtil
import io.github.peerapongsam.heropedia.data.repo.HeroRepository
import io.github.peerapongsam.heropedia.data.repo.HeroRepositoryImpl
import io.github.peerapongsam.heropedia.domain.executor.PostExecutionThread
import io.github.peerapongsam.heropedia.domain.executor.ThreadExecutor
import io.github.peerapongsam.heropedia.domain.usecase.GetHeroDetailUseCase
import io.github.peerapongsam.heropedia.domain.usecase.GetHeroesUseCase
import io.github.peerapongsam.heropedia.ui.UiThread
import io.github.peerapongsam.heropedia.ui.detail.HeroDetailViewModelFactory
import io.github.peerapongsam.heropedia.ui.heroes.HeroesViewModelFactory

object InjectorUtil {

    private fun provideHeropediaService(): HeropediaService {
        return ServiceUtil.heropediaService
    }

    private fun provideHeroRepository(): HeroRepository {
        return HeroRepositoryImpl(provideHeropediaService())
    }

    private fun provideThreadExecutor(): ThreadExecutor {
        return JobExecutor()
    }

    private fun providePostExecutionThread(): PostExecutionThread {
        return UiThread()
    }

    private fun provideGetHeroesUseCase(): GetHeroesUseCase {
        return GetHeroesUseCase(provideThreadExecutor(), providePostExecutionThread(), provideHeroRepository())
    }

    private fun provideGetHeroDetailUseCase(): GetHeroDetailUseCase {
        return GetHeroDetailUseCase(provideThreadExecutor(), providePostExecutionThread(), provideHeroRepository())
    }

    fun provideHeroesViewModelFactory(): HeroesViewModelFactory {
        return HeroesViewModelFactory(provideGetHeroesUseCase())
    }

    fun provideHeroDetailViewModelFactory(): HeroDetailViewModelFactory {
        return HeroDetailViewModelFactory(provideGetHeroDetailUseCase())
    }
}
