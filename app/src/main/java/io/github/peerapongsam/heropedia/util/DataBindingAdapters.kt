package io.github.peerapongsam.heropedia.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter(value = ["heroImageUrl"])
fun setHeroImageUrl(imageView: ImageView, heroId: String?) {
    if (heroId.isNullOrEmpty()) {
        Glide.with(imageView).clear(imageView)
    } else {
        Glide.with(imageView)
            .load("https://steamcdn-a.akamaihd.net/apps/dota2/images/heroes/${heroId}_vert.jpg")
            .into(imageView)
    }
}
