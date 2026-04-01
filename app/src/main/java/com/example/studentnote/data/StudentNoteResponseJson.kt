package com.example.studentnote.data

data class StudentNoteListJson(
    val results: List<StudentJson>
)

data class StudentJson(
    val _id: String,
    val libelle_academie: String,
    val discipline: String,
    val competence: String,
    val taux_de_maitrise: Float
)
