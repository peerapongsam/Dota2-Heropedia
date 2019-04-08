package io.github.peerapongsam.heropedia.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Portrait(
    @SerializedName("full") val full: String,
    @SerializedName("vert") val vert: String
) : Parcelable
