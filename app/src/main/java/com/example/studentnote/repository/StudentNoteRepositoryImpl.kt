package com.example.studentnote.repository

import com.example.studentnote.data.Student
import com.example.studentnote.data.StudentResponse
import com.example.studentnote.webservice.StudentNoteWebService
import kotlinx.coroutines.flow.MutableSharedFlow
import okio.IOException
import javax.inject.Inject

class StudentNoteRepositoryImpl @Inject constructor(
    private val studentNoteWebService: StudentNoteWebService,
) : StudentNoteRepository {

    override val studentResponse = MutableSharedFlow<StudentResponse>()

    override suspend fun updateListStudent() {
        try {
            studentResponse.emit(StudentResponse.Pending)

            val response = studentNoteWebService.getStudentsList(100)

            val list = response.results.map {
                Student(
                    id = it._id,
                    academie = it.libelle_academie,
                    discipline = it.discipline,
                    competences = it.competence,
                    taux = it.taux_de_maitrise.toDouble()
                )
            }

            studentResponse.emit(StudentResponse.Success(list))
        } catch (e: IOException) {
            studentResponse.emit(StudentResponse.Success(emptyList()))
        }
    }
}