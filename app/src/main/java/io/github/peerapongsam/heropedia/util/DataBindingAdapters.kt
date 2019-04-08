package io.github.peerapongsam.heropedia.util

import android.widget.ImageView
import android.widget.TextView
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

@BindingAdapter(value = ["roles"])
fun setHeroRoles(textView: TextView, roles: List<String>?) {
    textView.text = roles?.joinToString(" - ") ?: ""
}
