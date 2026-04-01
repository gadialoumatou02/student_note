package com.example.studentnote.data

sealed interface StudentResponse {
    data object Pending : StudentResponse
    @JvmInline
    value class Success(val list: List<Student>) : StudentResponse
}