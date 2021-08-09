package com.ruben.domain.interactors

import com.ruben.entities.Result
import com.ruben.entities.localmodels.courses.CoursesItemLocal

interface CoursesListInteractor {

    suspend fun getCoursesListResponse(): Result<List<CoursesItemLocal>>
    suspend fun getCoursesListFromDb(): Result<List<CoursesItemLocal>>
}