package com.elvanerdem.citydistrict.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class People(val name: Map<String, Person>)