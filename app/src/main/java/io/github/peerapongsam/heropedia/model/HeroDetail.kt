package io.github.peerapongsam.heropedia.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HeroDetail(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("attack") val attack: String?,
    @SerializedName("roles") val roles: List<String>?,
    @SerializedName("overview") val overview: Overview?
) : Parcelable

@Parcelize
data class Overview(
    @SerializedName("attribs") val attribs: Attribs?,
    @SerializedName("abilities") val abilities: List<Ability>?
) : Parcelable

@Parcelize
data class Attribs(
    @SerializedName("str") val strength: Strength?,
    @SerializedName("agi") val agility: Agility?,
    @SerializedName("int") val intelligence: Intelligence?,
    @SerializedName("dmg") val damage: Damage?,
    @SerializedName("ms") val moveSpeed: String?,
    @SerializedName("armor") val armor: String?
) : Parcelable

@Parcelize
data class Strength(
    @SerializedName("base") val base: String?,
    @SerializedName("gain") val gain: String?
) : Parcelable

@Parcelize
data class Agility(
    @SerializedName("base") val base: String?,
    @SerializedName("gain") val gain: String?
) : Parcelable

@Parcelize
data class Intelligence(
    @SerializedName("base") val base: String?,
    @SerializedName("gain") val gain: String?
) : Parcelable

@Parcelize
data class Damage(
    @SerializedName("min") val min: String?,
    @SerializedName("max") val max: String?
) : Parcelable

@Parcelize
data class Ability(
    @SerializedName("icon") val icon: String?,
    @SerializedName("name") val name: String?
) : Parcelable
