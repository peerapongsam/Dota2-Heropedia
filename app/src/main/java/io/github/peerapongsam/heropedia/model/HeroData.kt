package io.github.peerapongsam.heropedia.model

import com.google.gson.annotations.SerializedName

data class HeroData(
    @SerializedName("u") val uniqueId: String,
    @SerializedName("dname") val name: String,
    @SerializedName("pa") val pa: String,
    @SerializedName("dac") val dac: String,
    @SerializedName("droles") val droles: String
)
