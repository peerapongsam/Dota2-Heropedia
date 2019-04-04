package io.github.peerapongsam.heropedia.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import io.github.peerapongsam.heropedia.data.ApiService
import io.github.peerapongsam.heropedia.data.Service
import io.github.peerapongsam.heropedia.model.Hero
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HeroesViewModel : ViewModel() {

    @Suppress("PropertyName")
    private val TAG = "HeroesViewModel"

    private var _heroType = MutableLiveData<String>()
    private val result = Transformations.switchMap(_heroType) { type ->
        Log.d(TAG, "Transformations.map() called with: type = [$type]")
        loadHeroes(type)
        _heroType
    }

    val heroes = MutableLiveData<List<Hero>>()

    private val service = Service.defaultService.create(ApiService::class.java)

    private val disposables = CompositeDisposable()

    fun loadHeroes(heroType: String) {
        disposables.add(
            service.getHeropediaData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { it.heroes.entries }
                .flatMapIterable { it }
                .filter { it.value.pa.isNotEmpty() && it.value.pa == heroType }
                .map { Hero(it.key, it.value.name) }
                .toList()
                .subscribe({ data ->
                    Log.d(TAG, "loadHeroes() called with: data = [$data]")
                    heroes.postValue(data)
                }, { error ->
                    Log.d(TAG, "loadHeroes() called with: error = [$error]")
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
