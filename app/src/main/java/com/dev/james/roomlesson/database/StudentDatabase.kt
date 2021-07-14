package com.dev.james.roomlesson.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dev.james.roomlesson.models.Student

@Database(
    entities = [Student::class] ,
    version = 1 )
abstract class StudentDatabase : RoomDatabase() {

    abstract fun studentDao() : StudentDao

    companion object {
        //make database singleton to prevent multiple instances of it being created
        @Volatile
        private var INSTANCE : StudentDatabase? = null

        fun getDatabase(context : Context) : StudentDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StudentDatabase::class.java,
                    "student_database",
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance

                // return instance
                instance
            }
        }
    }
}