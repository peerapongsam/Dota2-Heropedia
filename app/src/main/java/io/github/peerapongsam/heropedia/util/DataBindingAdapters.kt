package io.github.peerapongsam.heropedia.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import timber.log.Timber

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

@BindingAdapter(value = ["abilityImageUrl"])
fun setAbilityImageUrl(imageView: ImageView, url: String?) {
    Timber.d("setAbilityImageUrl() called with: imageView = [$imageView], url = [$url]")
    if (url.isNullOrEmpty()) {
        Glide.with(imageView).clear(imageView)
    } else {
        Glide.with(imageView)
            .load(url)
            .into(imageView)
    }
}
