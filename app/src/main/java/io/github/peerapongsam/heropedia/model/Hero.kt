package io.github.peerapongsam.heropedia.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Hero(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("primary_attribs") val primaryAttribs: String,
    @SerializedName("attack") val dac: String,
    @SerializedName("roles") val roles: List<String>,
    @SerializedName("portrait") val portrait: Portrait
) : Parcelable {
}
