package com.btntrung.pointmanagement.presentation.manager.studentlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.btntrung.pointmanagement.data.remote.semester.SemesterService
import com.btntrung.pointmanagement.data.remote.student.StudentService
import com.btntrung.pointmanagement.entity.Classroom
import com.btntrung.pointmanagement.entity.Semester
import com.btntrung.pointmanagement.entity.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class StudentListViewModel(private val studentService: StudentService) : ViewModel() {
    val classroom = MutableLiveData<Classroom>()

    private val _students = MutableLiveData<List<Student>>()
    val students : LiveData<List<Student>> get() = _students

    fun getStudents(classId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = studentService.getStudentsByClass(classId)
                _students.postValue(response)
                Timber.d("VM $response")
            }
        }
    }
}