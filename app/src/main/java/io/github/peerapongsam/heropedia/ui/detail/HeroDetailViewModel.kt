package io.github.peerapongsam.heropedia.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.peerapongsam.heropedia.domain.usecase.GetHeroDetailUseCase
import io.github.peerapongsam.heropedia.domain.usecase.GetHeroDetailUseCase.Params
import io.github.peerapongsam.heropedia.model.HeroDetail
import io.reactivex.observers.DisposableObserver
import timber.log.Timber

class HeroDetailViewModel(
    private val getHeroDetailUseCase: GetHeroDetailUseCase
) : ViewModel() {

    private val _heroId = MutableLiveData<String>()

    private val _heroDetail = MediatorLiveData<HeroDetail>()
    val heroDetail: LiveData<HeroDetail> = _heroDetail

    init {
        _heroDetail.addSource(_heroId) {
            getHeroDetailUseCase.execute(GetHeroDetailObserver(), Params(id = it))
        }
    }

    fun setHeroId(id: String) {
        if (_heroId.value == id) return
        _heroId.value = id
    }

    override fun onCleared() {
        getHeroDetailUseCase.dispose()
        super.onCleared()
    }

    inner class GetHeroDetailObserver : DisposableObserver<HeroDetail>() {

        override fun onComplete() {
            Timber.d("onComplete() called")
        }

        override fun onNext(detail: HeroDetail) {
            _heroDetail.postValue(detail)
            Timber.d("onNext() called with: detail = [$detail]")
        }

        override fun onError(throwable: Throwable) {
            Timber.d("onError() called with: throwable = [$throwable]")
        }
    }
}
