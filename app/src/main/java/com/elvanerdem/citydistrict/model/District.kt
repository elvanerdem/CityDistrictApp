package com.elvanerdem.citydistrict.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class District: Detail() {
        var districtName: String = ""
        var population: String = ""
}

        



