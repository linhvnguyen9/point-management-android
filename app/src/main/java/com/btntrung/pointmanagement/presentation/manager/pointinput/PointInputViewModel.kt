package com.btntrung.pointmanagement.presentation.manager.pointinput

import androidx.lifecycle.*
import com.btntrung.pointmanagement.data.remote.point.PointService
import com.btntrung.pointmanagement.data.remote.subject.SubjectService
import com.btntrung.pointmanagement.entity.Point
import com.btntrung.pointmanagement.entity.Subject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PointInputViewModel(
    private val subjectService: SubjectService,
    private val pointService: PointService
) : ViewModel() {
    val attendance = MutableLiveData<String>()
    val test = MutableLiveData<String>()
    val project = MutableLiveData<String>()
    val final = MutableLiveData<String>()

    private val _avg = MediatorLiveData<String>()
    val avg: LiveData<String> get() = _avg

    private val _grade = MediatorLiveData<String>()
    val grade: LiveData<String> get() = _grade

    private val _subject = MutableLiveData<Subject>()
    val subject: LiveData<Subject> get() = _subject

    private val _savePointResponse = MutableLiveData<Boolean>()
    val savePointResponse : LiveData<Boolean> get() = _savePointResponse

    init {
        _avg.addSource(attendance) {
            calculateAvgPoint()
        }

        _avg.addSource(test) {
            calculateAvgPoint()
        }
        _avg.addSource(project) {
            calculateAvgPoint()
        }
        _avg.addSource(final) {
            calculateAvgPoint()
        }
    }

    fun getSubjectDetail(subjectId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = subjectService.getSubjects().find { it.id == subjectId }
                _subject.postValue(response!!)
            }
        }
    }

    fun savePoint(studentUid: String, semesterId: Int, managerId: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = pointService.savePoint(
                    Point(
                        attendance.value?.toFloat() ?: 0.0f,
                        test.value?.toFloat() ?: 0.0f,
                        project.value?.toFloat() ?: 0.0f,
                        final.value?.toFloat() ?: 0.0f,
                        avg.value?.toFloat() ?: 0.0f,
                        studentUid,
                        semesterId,
                        managerId,
                        _subject.value?.id ?: 0,
                    )
                )

                if (response.isSuccessful) {
                    _savePointResponse.postValue(true)
                }
            }
        }
    }

    private fun calculateAvgPoint() {
        val attendanceValue = attendance.value?.toFloatOrNull()
        val testValue = test.value?.toFloatOrNull()
        val projectValue = project.value?.toFloatOrNull()
        val finalValue = final.value?.toFloatOrNull()

        val subjectAttendance = _subject.value?.attendancePercent?.times(0.01f)
        val subjectTest = _subject.value?.testPercent?.times(0.01f)
        val subjectProject = _subject.value?.projectPercent?.times(0.01f)
        val subjectFinal = _subject.value?.finalPercent?.times(0.01f)

        if (attendanceValue != null && testValue != null && projectValue != null && finalValue != null && subjectAttendance != null && subjectTest != null && subjectProject != null && subjectFinal != null) {
            val avg =
                (attendanceValue * subjectAttendance + testValue * subjectTest + projectValue * subjectProject + subjectFinal * finalValue)
            _avg.value =
                avg.toString()
            _grade.value = calculateGrade(avg)
        }
    }

    private fun calculateGrade(avg: Float): String {
        return when (avg) {
            in 0.0..3.99 -> "F"
            in 4.0..4.99 -> "D"
            in 5.0..5.9 -> "D+"
            in 5.5..6.49 -> "C"
            in 6.5..6.99 -> "C+"
            in 7.0..7.99 -> "B"
            in 8.0..8.49 -> "B+"
            in 8.5..8.99 -> "A"
            in 9.0..10.0 -> "A+"
            else -> ""
        }
    }
}