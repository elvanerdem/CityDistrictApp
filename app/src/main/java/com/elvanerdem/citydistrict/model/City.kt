package com.elvanerdem.citydistrict.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class City: Detail() {
    var name: String = ""
    var plate: String = ""
    var areaCode: String = ""
    var region: String = ""
    var districts: List<District> = listOf()
}