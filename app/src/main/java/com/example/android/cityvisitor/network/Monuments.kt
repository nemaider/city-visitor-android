package com.example.android.cityvisitor.network

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize


@JsonClass(generateAdapter = true)
@Parcelize
data class Monuments(
    val name: String,
    val info: String,
    val category: String,
    val rate: Float,
    val website: String,
    val geo: LatLong
 ): Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class LatLong(
    val lat: Double,
    @Json(name = "lng")
    val lng: Double
) : Parcelable


@Parcelize
data class MonumentResponse(
        @Json(name = "filtersDTO") val filters: Filter,
        @Json(name = "monumentDTO") val monuments: List<Monuments>
): Parcelable

@Parcelize
data class Filter(
        @Json(name = "categories") val regions: List<String>
): Parcelable
