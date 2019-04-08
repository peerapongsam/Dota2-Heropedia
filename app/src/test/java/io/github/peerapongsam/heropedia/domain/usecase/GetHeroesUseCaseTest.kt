package io.github.peerapongsam.heropedia.domain.usecase

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.github.peerapongsam.heropedia.data.repo.HeroRepository
import io.github.peerapongsam.heropedia.domain.executor.PostExecutionThread
import io.github.peerapongsam.heropedia.domain.executor.ThreadExecutor
import io.github.peerapongsam.heropedia.domain.util.HeroesUtil
import io.github.peerapongsam.heropedia.model.Hero
import io.reactivex.Observable
import org.junit.*

class GetHeroesUseCaseTest {

    lateinit var getHeroesUseCase: GetHeroesUseCase

    lateinit var mockThreadExecutor: ThreadExecutor
    lateinit var mockPostExecutionThread: PostExecutionThread
    lateinit var mockHeroRepository: HeroRepository

    @Before
    fun setUp() {
        mockThreadExecutor = mock()
        mockPostExecutionThread = mock()
        mockHeroRepository = mock()

        getHeroesUseCase = GetHeroesUseCase(mockThreadExecutor, mockPostExecutionThread, mockHeroRepository)
    }

    @Test
    fun test_use_case_call_repository() {
        getHeroesUseCase.create()
        verify(mockHeroRepository).getHeroes()
    }

    @Test
    fun test_use_case_completes() {
        stubHeroRepositoryGetHeroes(Observable.just(HeroesUtil.makeHeroes(2)))
        val testObserver = getHeroesUseCase.create().test()
        testObserver.assertComplete()
    }

    @Test
    fun test_use_case_return_data() {
        val heroes = HeroesUtil.makeHeroes(10)
        stubHeroRepositoryGetHeroes(Observable.just(heroes))
        val testObserver = getHeroesUseCase.create().test()
        testObserver.assertValue(heroes)
    }

    private fun stubHeroRepositoryGetHeroes(observable: Observable<List<Hero>>) {
        whenever(mockHeroRepository.getHeroes())
            .thenReturn(observable)
    }
}
