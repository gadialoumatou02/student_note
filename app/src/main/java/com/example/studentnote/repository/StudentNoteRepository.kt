package com.example.studentnote.repository

import com.example.studentnote.data.StudentResponse
import kotlinx.coroutines.flow.Flow

interface StudentNoteRepository {

    val studentResponse: Flow<StudentResponse>

    suspend fun updateListStudent()
}