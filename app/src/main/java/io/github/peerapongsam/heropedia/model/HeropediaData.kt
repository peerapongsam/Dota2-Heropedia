package io.github.peerapongsam.heropedia.model

import com.google.gson.annotations.SerializedName

data class HeropediaData(
    @SerializedName("herodata")
    val heroes: Map<String, HeroData>
)
