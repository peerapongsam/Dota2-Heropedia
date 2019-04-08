package io.github.peerapongsam.heropedia.ui.detail

import android.view.ViewTreeObserver.OnPreDrawListener
import androidx.lifecycle.Observer
import io.github.peerapongsam.heropedia.BR
import io.github.peerapongsam.heropedia.InjectorUtil
import io.github.peerapongsam.heropedia.R
import io.github.peerapongsam.heropedia.databinding.ActivityHeroDetailBinding
import io.github.peerapongsam.heropedia.ui.base.BaseActivity
import io.github.peerapongsam.heropedia.util.viewModelProvider
import timber.log.Timber

class HeroDetailActivity : BaseActivity<ActivityHeroDetailBinding>() {

    override val layoutRes: Int
        get() = R.layout.activity_hero_detail

    private lateinit var viewModel: HeroDetailViewModel

    override fun createViewModel() {
        val viewModelFactory = InjectorUtil.provideHeroDetailViewModelFactory()
        viewModel = viewModelProvider(viewModelFactory)
        intent.getStringExtra(EXTRA_HERO)?.let { it ->
            Timber.d("createViewModel() called with: it = [$it]")
            viewModel.setHeroId(it)
        }
    }

    override fun setUpView(binding: ActivityHeroDetailBinding) {
        super.setUpView(binding)
        viewModel.heroDetail.observe(this, Observer { it ->
            Timber.d("setUpView() called with: it = [$it]")
            binding.setVariable(BR.hero, it)
        })

        supportPostponeEnterTransition()
        binding.image.viewTreeObserver.addOnPreDrawListener(object : OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                binding.image.viewTreeObserver.removeOnPreDrawListener(this)
                supportStartPostponedEnterTransition()
                return true
            }
        })
    }

    companion object {

        const val EXTRA_HERO = "io.github.peerapongsam.heropedia.intent.extra.HERO"
    }
}
