package com.elvanerdem.citydistrict.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Person(val name: String, val surname: String)