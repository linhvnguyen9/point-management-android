package com.btntrung.pointmanagement.presentation.manager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.btntrung.pointmanagement.data.remote.semester.ClassroomService
import com.btntrung.pointmanagement.data.remote.semester.SemesterService
import com.btntrung.pointmanagement.entity.Classroom
import com.btntrung.pointmanagement.entity.Semester
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class ManagerMainViewModel(private val semesterService: SemesterService, private val classroomService: ClassroomService) : ViewModel() {
    private val _semesters = MutableLiveData<List<Semester>>()
    val semesters : LiveData<List<Semester>> get() = _semesters

    private val _classroom = MutableLiveData<List<Classroom>>()
    val classroom : LiveData<List<Classroom>> get() = _classroom

    init {
        getAllSemesters()
        getClassrooms(6)
    }

    private fun getAllSemesters() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = semesterService.getSemesters()
                _semesters.postValue(response)
                Timber.d("VM $response")
            }
        }
    }

    private fun getClassrooms(semesterId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = classroomService.getClassroomBySemester(semesterId)
                _classroom.postValue(response)
                Timber.d("get classrooms $response")
            }
        }
    }
}