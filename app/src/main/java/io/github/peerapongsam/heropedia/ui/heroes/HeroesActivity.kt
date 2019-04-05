package io.github.peerapongsam.heropedia.ui.heroes

import io.github.peerapongsam.heropedia.InjectorUtil
import io.github.peerapongsam.heropedia.R
import io.github.peerapongsam.heropedia.databinding.ActivityHeroesBinding
import io.github.peerapongsam.heropedia.ui.base.BaseActivity
import io.github.peerapongsam.heropedia.util.viewModelProvider

class HeroesActivity : BaseActivity<ActivityHeroesBinding>() {

    override val layoutRes: Int
        get() = R.layout.activity_heroes

    private lateinit var viewModel: HeroesViewModel

    override fun createViewModel() {
        val viewModelFactory = InjectorUtil.provideHeroesViewModelFactory()
        viewModel = viewModelProvider(viewModelFactory)
    }

    override fun setUpView(binding: ActivityHeroesBinding) {
        binding.run {
            setSupportActionBar(toolbar)
            heroesPage.adapter = HeroesPagerAdapter(supportFragmentManager)
            heroesTabs.setupWithViewPager(heroesPage)
        }
    }
}
