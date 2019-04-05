package io.github.peerapongsam.heropedia.ui.detail

import io.github.peerapongsam.heropedia.BR
import io.github.peerapongsam.heropedia.R
import io.github.peerapongsam.heropedia.databinding.ActivityHeroDetailBinding
import io.github.peerapongsam.heropedia.model.Hero
import io.github.peerapongsam.heropedia.ui.base.BaseActivity

class HeroDetailActivity : BaseActivity<ActivityHeroDetailBinding>() {

    override val layoutRes: Int
        get() = R.layout.activity_hero_detail

    override fun setUpView(binding: ActivityHeroDetailBinding) {
        super.setUpView(binding)
        binding.setVariable(BR.hero, intent.getParcelableExtra<Hero>(EXTRA_HERO))
    }

    companion object {
        const val EXTRA_HERO = "io.github.peerapongsam.heropedia.intent.extra.HERO"
    }
}
