package io.github.peerapongsam.heropedia.domain.usecase

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.github.peerapongsam.heropedia.data.repo.HeroRepository
import io.github.peerapongsam.heropedia.domain.executor.PostExecutionThread
import io.github.peerapongsam.heropedia.domain.executor.ThreadExecutor
import io.github.peerapongsam.heropedia.domain.usecase.GetHeroDetailUseCase.Params
import io.github.peerapongsam.heropedia.domain.util.HeroesUtil
import io.github.peerapongsam.heropedia.model.HeroDetail
import io.reactivex.Observable
import org.junit.*

class GetHeroDetailUseCaseTest {

    lateinit var getHeroDetailUseCase: GetHeroDetailUseCase

    lateinit var mockThreadExecutor: ThreadExecutor
    lateinit var mockPostExecutionThread: PostExecutionThread
    lateinit var mockHeroRepository: HeroRepository

    @Before
    fun setUp() {
        mockThreadExecutor = mock()
        mockPostExecutionThread = mock()
        mockHeroRepository = mock()

        getHeroDetailUseCase = GetHeroDetailUseCase(mockThreadExecutor, mockPostExecutionThread, mockHeroRepository)
    }

    @Test
    fun test_use_case_call_repository() {
        getHeroDetailUseCase.create(Params(""))
        verify(mockHeroRepository).getHeroDetail("")
    }

    @Test
    fun test_use_case_completes() {
        stubHeroRepositoryGetHeroDetail(Observable.just(HeroesUtil.makeHeroDetail()), "")
        val testObserver = getHeroDetailUseCase.create(Params("")).test()
        testObserver.assertComplete()
    }

    @Test
    fun test_use_case_return_data() {
        val heroDetail = HeroesUtil.makeHeroDetail()
        stubHeroRepositoryGetHeroDetail(Observable.just(heroDetail), "")
        val testObserver = getHeroDetailUseCase.create(Params("")).test()
        testObserver.assertValue(heroDetail)
    }

    private fun stubHeroRepositoryGetHeroDetail(observable: Observable<HeroDetail>, id: String) {
        whenever(mockHeroRepository.getHeroDetail(id))
            .thenReturn(observable)
    }
}
