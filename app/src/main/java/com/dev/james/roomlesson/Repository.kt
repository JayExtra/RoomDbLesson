package com.dev.james.roomlesson

import com.dev.james.roomlesson.database.StudentDao
import com.dev.james.roomlesson.models.Student
import kotlinx.coroutines.flow.Flow

class Repository(private val studentDao : StudentDao) {


    //get all students
    val allStudents: Flow<List<Student>> = studentDao.getAllStudents()

    //insert a student into database
    suspend fun insertStudents(student: Student) {
        studentDao.addStudent(student)
    }

    //delete student
    suspend fun deleteStudent(studentId : Int) {
        studentDao.deleteStudent(studentId)
    }

    //delete all students
    suspend fun deleteAllStudents(){
        studentDao.deleteAll()
    }

}