package com.example.android.cityvisitor.add;

import com.example.android.cityvisitor.network.LatLong
import com.example.android.cityvisitor.network.Monuments

class MonumentCreator {
    var name = ""
    var description = ""
    var category = ""
    var url = ""
    var rate = 0.0f
    var lat = 0.0
    var lng = 0.0


    fun createMonument() : Monuments{
        return Monuments(
            name = name,
            info = description,
            category = category,
            website = url,
            rate = rate,
            geo = LatLong(lat,lng)
        )
    }
}
