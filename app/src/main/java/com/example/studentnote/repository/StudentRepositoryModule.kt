package com.example.studentnote.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class StudentRepositoryModule {

    @Binds
    abstract fun bindStudentRepository(
        studentRepositoryImpl: StudentNoteRepositoryImpl
    ): StudentNoteRepository
}