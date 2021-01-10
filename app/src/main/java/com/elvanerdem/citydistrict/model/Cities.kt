package com.elvanerdem.citydistrict.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Cities(
        val cities: List<City>
)