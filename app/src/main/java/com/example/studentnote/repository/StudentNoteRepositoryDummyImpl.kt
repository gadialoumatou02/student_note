package com.example.studentnote.repository

import com.example.studentnote.data.Student
import com.example.studentnote.data.StudentResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

class StudentNoteRepositoryDummyImpl @Inject constructor() : StudentNoteRepository {
    override val studentResponse = MutableSharedFlow<StudentResponse>()

    override suspend fun updateListStudent() {
        studentResponse.emit(StudentResponse.Pending)

        delay(1000)

        studentResponse.emit(
            StudentResponse.Success(
                listOf(
                    Student(
                        "1",
                        "PARIS",
                        "Mathématiques",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                        30.12
                    ),
                    Student(
                        "2",
                        "PARIS",
                        "Français",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                        12.45
                    )
                )
            )
        )
    }
}