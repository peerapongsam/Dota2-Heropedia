package io.github.peerapongsam.heropedia.ui.heroes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.peerapongsam.heropedia.domain.usecase.GetHeroesUseCase

class HeroesViewModelFactory(
    private val getHeroesUseCase: GetHeroesUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HeroesViewModel(getHeroesUseCase) as T
    }
}
