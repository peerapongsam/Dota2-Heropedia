package io.github.peerapongsam.heropedia.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HeroDetail(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("attack") val attack: String,
    @SerializedName("roles") val roles: List<String>
) : Parcelable
