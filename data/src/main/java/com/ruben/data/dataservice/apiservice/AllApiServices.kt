package com.ruben.data.dataservice.apiservice

import com.ruben.data.utils.AppURLS
import com.ruben.entities.Result
import com.ruben.entities.responcemodels.CoursesItemResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface AllApiServices {

    @Headers("Accept:application/json")
    @GET(AppURLS.GET_COURSES)
    suspend fun getCourses(
        @Header("Authorization") token: String?,
        @Header("Accept-Language") language: String?
    ): Result<List<CoursesItemResponse>>
}
