package com.ruben.entities.localmodels.courses

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses")
data class CoursesItemLocal(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val courseName: String,

    val iconUrl: String,

    val price: Int,

    var isChecked: Boolean = false
)
