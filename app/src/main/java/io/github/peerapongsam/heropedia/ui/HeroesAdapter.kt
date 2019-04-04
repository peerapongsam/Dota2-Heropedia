package io.github.peerapongsam.heropedia.ui

import android.view.ViewGroup
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        return HeroesViewHolder(parent)
    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        holder.bindValue(getItem(position))
    }
}
