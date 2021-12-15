package com.example.android.cityvisitor.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize



@Parcelize
data class GdgChapter(
    val name: String,
    @Json(name = "info") val city: String,
    val category: String,
    val rate: Float,
    val website: String,
    val geo: LatLong
 ): Parcelable

@Parcelize
data class LatLong(
    val lat: Double,
    @Json(name = "lng")
    val long: Double
) : Parcelable

@Parcelize
data class GdgResponse(
        @Json(name = "filtersDTO") val filters: Filter,
        @Json(name = "monumentDTO") val chapters: List<GdgChapter>
): Parcelable

@Parcelize
data class Filter(
        @Json(name = "categories") val regions: List<String>
): Parcelable
