package com.elvanerdem.citydistrict.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class City(
        val name: String,
        var plate: String,
        var areaCode: String,
        var region: String,
        val districts: List<District>
)