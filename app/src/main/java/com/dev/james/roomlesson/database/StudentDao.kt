package com.dev.james.roomlesson.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dev.james.roomlesson.models.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {

    //create
    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun addStudent(student : Student)

    //read
    @Query("SELECT * FROM student_table")
    fun getAllStudents() : Flow<List<Student>>

    //delete
    @Query("DELETE FROM student_table")
    suspend fun deleteAll()

    //delete individual student
    @Query("DELETE FROM student_table WHERE id = :studentId")
    suspend fun deleteStudent(studentId : Int)

}