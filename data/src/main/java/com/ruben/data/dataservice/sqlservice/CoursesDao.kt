package com.ruben.data.dataservice.sqlservice

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ruben.entities.Result
import com.ruben.entities.localmodels.courses.CoursesItemLocal

@Dao
interface CoursesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCourses(cours: List<CoursesItemLocal>)

    @Query("DELETE FROM courses")
    suspend fun deleteAllCourses()

    @Query("SELECT * FROM courses")
    suspend fun getAllCourses(): Result<List<CoursesItemLocal>>
}