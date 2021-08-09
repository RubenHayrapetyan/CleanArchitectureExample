package com.ruben.domain.utils


import com.ruben.entities.localmodels.courses.CoursesItemLocal
import com.ruben.entities.responcemodels.*

fun CoursesItemResponse.toDomain(): CoursesItemLocal{
    val baseURL = "www.my.testurl.com"
    return CoursesItemLocal(
        id = id ?: -1,
        courseName = courseName ?: "",
        iconUrl = "$baseURL$iconUrl.png",
        price = price ?: 0
    )
}



