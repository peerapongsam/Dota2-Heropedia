package io.github.peerapongsam.heropedia.ui.heroes

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import io.github.peerapongsam.heropedia.model.Hero

class HeroesAdapter : ListAdapter<Hero, HeroesViewHolder>(object : ItemCallback<Hero?>() {
    override fun areItemsTheSame(oldItem: Hero, newItem: Hero): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean {
        return oldItem == newItem
    }
}) {

    private var clickListener: ((Hero, ImageView, TextView) -> Unit)? = null

    fun setOnClickListener(l: ((Hero, ImageView, TextView) -> Unit)?) {
        this.clickListener = l
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        return HeroesViewHolder(parent, clickListener)
    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        holder.bindValue(getItem(position))
    }
}
