package com.example.studentnote.data

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class StudentMoshiAdapter {
    @FromJson
    fun fromJson(studentNoteJson: StudentNoteListJson): List<Student> {
        return studentNoteJson.results.map { studentJson ->
            Student(
                id = studentJson._id,
                academie = studentJson.libelle_academie,
                discipline = studentJson.discipline,
                competences = studentJson.competence,
                taux = studentJson.taux_de_maitrise.toDouble()
            )
        }
    }
}