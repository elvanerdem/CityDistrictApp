package com.elvanerdem.citydistrict.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class District {
        var districtName: String = ""
        var plate: String = ""
        var areaCode: String = ""
        var population: String = ""
        var region: String = ""
        var info: String = ""
        var cityHallName: String = ""
        var cityHallPhone: String = ""
        var cityHallFax: String = ""
        var cityHallEMail: String = ""
        var cityHallWeb: String = "" 
        
}

        



