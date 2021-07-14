package com.dev.james.roomlesson

import android.app.Application
import com.dev.james.roomlesson.database.StudentDatabase

class StudentsApplication : Application(){

    val database by lazy { StudentDatabase.getDatabase(this) }
    val repository by lazy { Repository(database.studentDao()) }

}