package io.github.peerapongsam.heropedia.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Hero(
    val id: String,
    val name: String,
    val pa: String
) : Parcelable {
}
