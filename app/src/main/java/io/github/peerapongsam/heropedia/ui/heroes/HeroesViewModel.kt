package io.github.peerapongsam.heropedia.ui.heroes

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import io.github.peerapongsam.heropedia.domain.usecase.GetHeroesUseCase
import io.github.peerapongsam.heropedia.model.Hero
import io.github.peerapongsam.heropedia.model.Resource
import io.reactivex.observers.DisposableObserver
import timber.log.Timber

class HeroesViewModel(
    private val getHeroesUseCase: GetHeroesUseCase
) : ViewModel() {

    @VisibleForTesting
    val _resource = MutableLiveData<Resource<List<Hero>>>()

    @VisibleForTesting
    val _heroes = Transformations.map(_resource) { input: Resource<List<Hero>>? ->
        input?.data ?: emptyList()
    }

    init {
        _resource.postValue(Resource.loading())
        getHeroesUseCase.execute(GetHeroesObserver())
    }

    fun getHeroes(primaryAttrs: String): LiveData<List<Hero>> {
        return Transformations.switchMap(_heroes) { input: List<Hero>? ->
            MutableLiveData<List<Hero>>().apply { postValue(input?.filter { it.primaryAttribs == primaryAttrs }?.sortedBy { it.name }) }
        }
    }

    override fun onCleared() {
        getHeroesUseCase.dispose()
        super.onCleared()
    }

    inner class GetHeroesObserver : DisposableObserver<List<Hero>>() {

        override fun onNext(list: List<Hero>) {
            Timber.d("onNext() called with: list = [$list]")
            _resource.postValue(Resource.success(list))
        }

        override fun onError(throwable: Throwable) {
            Timber.d("onError() called with: throwable = [$throwable]")
            _resource.postValue(Resource.error(throwable))
        }

        override fun onComplete() {
            Timber.d("onComplete() called")
        }
    }
}
