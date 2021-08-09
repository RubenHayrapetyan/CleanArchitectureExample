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
    fun insertAllCourses(cours: List<CoursesItemLocal>)

    @Query("DELETE FROM courses")
    fun deleteAllCourses()

    @Query("SELECT * FROM courses")
    fun getAllCourses(): Result<List<CoursesItemLocal>>
}