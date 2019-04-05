package io.github.peerapongsam.heropedia.ui.heroes

import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import io.github.peerapongsam.heropedia.BR
import io.github.peerapongsam.heropedia.R
import io.github.peerapongsam.heropedia.R.layout
import io.github.peerapongsam.heropedia.databinding.ItemHeroBinding
import io.github.peerapongsam.heropedia.model.Hero
import io.github.peerapongsam.heropedia.ui.base.BaseViewHolder
import io.github.peerapongsam.heropedia.util.executeAfter

class HeroesViewHolder(
    parent: ViewGroup,
    private val clickListener: ((Hero, ImageView, TextView) -> Unit)? = null
) : BaseViewHolder<Hero, ItemHeroBinding>(parent, R.layout.item_hero) {

    override fun bindValue(item: Hero) {
        binding.executeAfter {
            setVariable(BR.hero, item)
            setVariable(BR.clickListener, OnClickListener {
                this@HeroesViewHolder.clickListener?.invoke(item, binding.imageView, binding.textView)
            })
        }
    }
}
