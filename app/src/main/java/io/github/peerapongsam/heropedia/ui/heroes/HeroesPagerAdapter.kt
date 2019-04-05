package io.github.peerapongsam.heropedia.ui.heroes

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.RecyclerView

class HeroesPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    private val recycledViewPool = RecyclerView.RecycledViewPool()

    override fun getItem(position: Int): Fragment {
        val fragment = HeroesFragment.newInstance(HERO_TYPES[position])
        fragment.viewPool = recycledViewPool
        return fragment
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
