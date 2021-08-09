package com.ruben.data.datastore

import com.ruben.entities.Result
import com.ruben.entities.localmodels.courses.CoursesItemLocal
import com.ruben.entities.responcemodels.CoursesItemResponse

interface CoursesListRepository {

    suspend fun getCoursesListResponse(): Result<List<CoursesItemResponse>>
    suspend fun getCoursesListFromDb(): Result<List<CoursesItemLocal>>
    suspend fun insetCoursesListDb(data: List<CoursesItemLocal>)
    suspend fun deleteAllCourses()
}