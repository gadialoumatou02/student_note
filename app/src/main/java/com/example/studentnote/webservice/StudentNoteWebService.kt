package com.example.studentnote.webservice

import com.example.studentnote.data.Student
import com.example.studentnote.data.StudentNoteListJson
import retrofit2.http.GET
import retrofit2.http.Query

interface StudentNoteWebService {

    @GET("records?include_app_metas=true")
    suspend fun getStudentsList(
        @Query("limit") limit : Int = 100
    ) : StudentNoteListJson
}