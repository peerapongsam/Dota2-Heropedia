package io.github.peerapongsam.heropedia.ui

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.peerapongsam.heropedia.model.Hero

@BindingAdapter(value = ["heroesItems"])
fun heroesItems(recyclerView: RecyclerView, heroes: List<Hero>?) {
    if (recyclerView.adapter == null) {
        recyclerView.adapter = HeroesAdapter()
    }

    (recyclerView.adapter as HeroesAdapter).apply {
        this.submitList(heroes ?: emptyList())
    }
}
