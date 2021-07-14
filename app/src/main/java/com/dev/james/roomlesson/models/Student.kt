package com.dev.james.roomlesson.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "student_table")
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val name : String,
    val department : String,
    val topic : String
) : Parcelable
