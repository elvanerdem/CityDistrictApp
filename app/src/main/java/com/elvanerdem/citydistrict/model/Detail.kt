package com.elvanerdem.citydistrict.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
open class Detail {
    var info: String = ""
    var cityHallName: String = ""
    var cityHallPhone: String = ""
    var cityHallFax: String = ""
    var cityHallEMail: String = ""
    var cityHallWeb: String = ""
}