package io.github.peerapongsam.heropedia.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class HeroesPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return HeroesFragment.newInstance(HERO_TYPES[position])
    }

    override fun getCount(): Int {
        return HERO_TYPES.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return HERO_TYPES_NAME[position]
    }

    companion object {
        private val HERO_TYPES = listOf("str", "agi", "int")
        private val HERO_TYPES_NAME = listOf("Strength", "Agility", "Intelligence")
    }
}
