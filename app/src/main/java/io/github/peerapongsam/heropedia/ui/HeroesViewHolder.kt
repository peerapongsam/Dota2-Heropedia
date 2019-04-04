package io.github.peerapongsam.heropedia.ui

import android.view.ViewGroup
import io.github.peerapongsam.heropedia.BR
import io.github.peerapongsam.heropedia.R.layout
import io.github.peerapongsam.heropedia.model.Hero
import io.github.peerapongsam.heropedia.util.executeAfter

class HeroesViewHolder(parent: ViewGroup) : BaseViewHolder<Hero>(parent,
    layout.item_hero
) {

    override fun bindValue(item: Hero) {
        binding.executeAfter {
            setVariable(BR.hero, item)
        }
    }
}
