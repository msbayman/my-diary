package com.example.mydiary

interface LocalRepository {

    suspend fun getALL(): List<Dairy>

    suspend fun deleteDiary(diary: Dairy)

    suspend fun addDiary(diary: Dairy)


}