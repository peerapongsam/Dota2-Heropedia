package io.github.peerapongsam.heropedia.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import io.github.peerapongsam.heropedia.R
import io.github.peerapongsam.heropedia.databinding.ActivityHeroesBinding

class HeroesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityHeroesBinding = DataBindingUtil.setContentView(this, R.layout.activity_heroes)
        binding.run {
            setSupportActionBar(toolbar)
            lifecycleOwner = this@HeroesActivity
            heroesPage.adapter = HeroesPagerAdapter(supportFragmentManager)
            heroesTabs.setupWithViewPager(heroesPage)
        }
    }
}
