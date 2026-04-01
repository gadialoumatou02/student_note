package com.example.studentnote.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentnote.data.Student
import com.example.studentnote.data.StudentResponse
import com.example.studentnote.repository.StudentNoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentNoteListViewModel @Inject constructor(
    private val studentRepository: StudentNoteRepository
) : ViewModel() {

    private val _studentList = MutableLiveData<List<Student>>(emptyList())
    val studentList: LiveData<List<Student>> = _studentList

    private val _isUpdating = MutableLiveData(false)
    val isUpdating: LiveData<Boolean> = _isUpdating

    init {
        viewModelScope.launch(Dispatchers.IO) {
            studentRepository.studentResponse.collect { response ->
                when (response) {
                    is StudentResponse.Pending -> _isUpdating.postValue(true)
                    is StudentResponse.Success -> {
                        _studentList.postValue(response.list)
                        _isUpdating.postValue(false)
                    }
                }
            }
        }
    }

    fun updateStudentNote() {
        viewModelScope.launch(Dispatchers.IO) {
            studentRepository.updateListStudent()
        }
    }
}