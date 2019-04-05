package io.github.peerapongsam.heropedia.ui.heroes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import io.github.peerapongsam.heropedia.domain.usecase.GetHeroesUseCase
import io.github.peerapongsam.heropedia.model.Hero
import io.github.peerapongsam.heropedia.model.HeropediaData
import io.reactivex.observers.DisposableObserver

class HeroesViewModel(
    private val getHeroesUseCase: GetHeroesUseCase
) : ViewModel() {

    @Suppress("PropertyName")
    private val TAG = "HeroesViewModel"

    init {
        getHeroesUseCase.execute(GetHeroesObserver())
    }

    val heroes = MutableLiveData<List<Hero>>()

    fun getHeroes(filterBy: String): LiveData<List<Hero>> {
        return Transformations.switchMap(heroes) { input: List<Hero>? ->
            MutableLiveData<List<Hero>>().apply { postValue(input?.filter { it.pa == filterBy }) }
        }
    }

    override fun onCleared() {
        super.onCleared()
        getHeroesUseCase.dispose()
    }

    inner class GetHeroesObserver : DisposableObserver<HeropediaData>() {

        override fun onNext(data: HeropediaData) {
            val list = data.heroes.entries.map {
                val key = it.key
                val value = it.value
                Hero(key, value.name, value.pa)
            }
            heroes.postValue(list)
        }

        override fun onError(throwable: Throwable) {
        }

        override fun onComplete() {
        }
    }
}
