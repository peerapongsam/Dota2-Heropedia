package io.github.peerapongsam.heropedia.ui.heroes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.KArgumentCaptor
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import io.github.peerapongsam.heropedia.domain.usecase.GetHeroesUseCase
import io.github.peerapongsam.heropedia.domain.util.HeroesUtil
import io.github.peerapongsam.heropedia.model.Hero
import io.github.peerapongsam.heropedia.model.Resource
import io.reactivex.observers.DisposableObserver
import org.junit.*
import org.junit.runner.*
import org.junit.runners.*
import org.mockito.*

@RunWith(JUnit4::class)
open class HeroesViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var getHeroesUseCase: GetHeroesUseCase

    @Captor
    private lateinit var captor: KArgumentCaptor<DisposableObserver<List<Hero>>>

    lateinit var heroesViewModel: HeroesViewModel

    @Before
    fun setUp() {
        captor = argumentCaptor()
        getHeroesUseCase = mock()
        heroesViewModel = HeroesViewModel(getHeroesUseCase)
    }

    @Test
    fun test_get_heroes_use_case_executed() {
        verify(getHeroesUseCase, times(1)).execute(any(), eq(null))
    }

    @Test
    fun test_get_heroes_use_case_return_success() {
        val heroes = HeroesUtil.makeHeroes(2)
        verify(getHeroesUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(heroes)
        println("test_get_heroes_use_case_return_success() called [${heroesViewModel._resource.value}], [$heroes]")
        assert(heroesViewModel._resource.value?.status == Resource.Status.SUCCESS)
    }

    @Test
    fun test_get_heroes_use_case_return_error() {
        verify(getHeroesUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())
        assert(heroesViewModel._resource.value?.status == Resource.Status.ERROR)
    }
}
