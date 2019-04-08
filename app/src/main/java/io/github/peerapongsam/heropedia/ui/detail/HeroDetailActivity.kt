package io.github.peerapongsam.heropedia.ui.detail

import android.util.Log
import androidx.lifecycle.Observer
import io.github.peerapongsam.heropedia.BR
import io.github.peerapongsam.heropedia.InjectorUtil
import io.github.peerapongsam.heropedia.R
import io.github.peerapongsam.heropedia.databinding.ActivityHeroDetailBinding
import io.github.peerapongsam.heropedia.model.Hero
import io.github.peerapongsam.heropedia.ui.base.BaseActivity
import io.github.peerapongsam.heropedia.util.viewModelProvider

class HeroDetailActivity : BaseActivity<ActivityHeroDetailBinding>() {

    override val layoutRes: Int
        get() = R.layout.activity_hero_detail

    private lateinit var viewModel: HeroDetailViewModel

    override fun createViewModel() {
        val viewModelFactory = InjectorUtil.provideHeroDetailViewModelFactory()
        viewModel = viewModelProvider(viewModelFactory)
        intent.getParcelableExtra<Hero>(EXTRA_HERO)?.let { it ->
            Log.d(TAG, "createViewModel() called with: it = [$it]")
            viewModel.setHeroId(it.id)
        }
    }

    override fun setUpView(binding: ActivityHeroDetailBinding) {
        super.setUpView(binding)
        viewModel.heroDetail.observe(this, Observer { it ->
            Log.d(TAG, "setUpView() called with: it = [$it]")
            binding.setVariable(BR.hero, it)
        })
    }

    companion object {

        private const val TAG = "HeroDetailActivity"

        const val EXTRA_HERO = "io.github.peerapongsam.heropedia.intent.extra.HERO"
    }
}
