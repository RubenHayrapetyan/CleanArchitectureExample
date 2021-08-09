package com.ruben.data.dataservice.sqlservice

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ruben.entities.localmodels.courses.CoursesItemLocal

@Database(
    entities = [CoursesItemLocal::class],
    version = 1,
    exportSchema = false)

//@TypeConverters(Converters::class)
abstract class AppDB : RoomDatabase() {

    abstract val coursesDao: CoursesDao
}