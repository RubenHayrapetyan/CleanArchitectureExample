package com.ruben.entities.responcemodels

import com.squareup.moshi.Json

class CoursesItemResponse(

    @field:Json(name = "id") val id: Int?,

    @field:Json(name = "name") val courseName: String?,

    @field:Json(name = "cat") val iconUrl: String?,

    @field:Json(name = "price") val price: Int?,
)