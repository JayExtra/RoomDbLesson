package com.dev.james.roomlesson

import android.util.Log
import androidx.lifecycle.*
import com.dev.james.roomlesson.models.Student
import kotlinx.coroutines.launch
import java.lang.Exception

class StudentsViewModel(private val repository: Repository) :ViewModel() {

    private val TAG = "ViewModelMess:"

    private var _updateMessages = MutableLiveData<Boolean>()
    val updateMessages get() = _updateMessages

    val allStudents : LiveData<List<Student>> = repository.allStudents.asLiveData()

    //inserting student
    fun addStudent(student: Student) = viewModelScope.launch {
        try {
            repository.insertStudents(student)
            _updateMessages.postValue(true)

        }catch (e : Exception){

            _updateMessages.postValue(false)
            Log.d(TAG, "addStudent: ${e.toString()} ")

        }
    }

    //delete all students
    fun deleteAllStudents() = viewModelScope.launch {
        try {
            repository.deleteAllStudents()
            _updateMessages.postValue(true)

        }catch (e : Exception){
            _updateMessages.postValue(false)
            Log.d(TAG, "addStudent: ${e.toString()} ")
        }
    }

    //delete a student
    fun addStudent(studentId : Int) = viewModelScope.launch {
        try {
            repository.deleteStudent(studentId)
            _updateMessages.postValue(true)

        }catch (e : Exception){
            _updateMessages.postValue(false)
            Log.d(TAG, "addStudent: ${e.toString()} ")

        }
    }
}

class StudentsViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StudentsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}