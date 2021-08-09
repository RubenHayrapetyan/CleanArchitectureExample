package com.ruben.data.repository

import com.ruben.data.dataservice.apiservice.AllApiServices
import com.ruben.data.dataservice.sqlservice.CoursesDao
import com.ruben.data.datastore.CoursesListRepository
import com.ruben.entities.Result
import com.ruben.entities.localmodels.courses.CoursesItemLocal
import com.ruben.entities.responcemodels.CoursesItemResponse

class CoursesListRepositoryImpl(
    private val allApiServices: AllApiServices,
    private val coursesListDao: CoursesDao
) : CoursesListRepository {

    override suspend fun getCoursesListResponse(): Result<List<CoursesItemResponse>> {
        return allApiServices.getCourses("MyToken", "Header")
    }

    override suspend fun getCoursesListFromDb(): Result<List<CoursesItemLocal>> {
        return coursesListDao.getAllCourses()
    }

    override suspend fun insetCoursesListDb(data: List<CoursesItemLocal>) {
        coursesListDao.insertAllCourses(data)
    }

    override suspend fun deleteAllCourses() {
        coursesListDao.deleteAllCourses()
    }

}
