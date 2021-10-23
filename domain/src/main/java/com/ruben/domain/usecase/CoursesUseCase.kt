package com.ruben.domain.usecase

import com.ruben.data.datastore.CoursesListRepository
import com.ruben.domain.interactors.CoursesListInteractor
import com.ruben.domain.utils.toDomain
import com.ruben.entities.CallException
import com.ruben.entities.Result
import com.ruben.entities.localmodels.courses.CoursesItemLocal

class CoursesUseCase(
    private val coursesListRepository: CoursesListRepository
) : CoursesListInteractor {

    private var coursesList = listOf<CoursesItemLocal>()

    override suspend fun getCoursesListResponse(): Result<List<CoursesItemLocal>> {

        return when(val result = coursesListRepository.getCoursesListResponse()){
            is Result.Success -> {
              val mappingValue = result.data?.map {
                  it.toDomain()
              }
                if (mappingValue != null) {
                    coursesList = mappingValue
                    Result.Success(coursesList)
                }
                else {
                    Result.Error(CallException(10))
                }

            }
            is Result.Error -> {
                Result.Error(CallException(10))
            }
        }
    }


    override suspend fun getCoursesListFromDb(): Result<List<CoursesItemLocal>> =
        coursesListRepository.getCoursesListFromDb()
}