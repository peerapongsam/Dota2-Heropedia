package io.github.peerapongsam.heropedia.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.peerapongsam.heropedia.domain.usecase.GetHeroDetailUseCase

class HeroDetailViewModelFactory(val getHeroDetailUseCase: GetHeroDetailUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HeroDetailViewModel(getHeroDetailUseCase) as T
    }
}
