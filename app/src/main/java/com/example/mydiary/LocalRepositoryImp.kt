package com.example.mydiary

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalRepositoryImp(private val db: diaryDataBase) : LocalRepository {
    override suspend fun getALL() =
        withContext(Dispatchers.IO) {
            db.diaryDao().getALL()
        }

    override suspend fun deleteDiary(diary: Dairy) {

        withContext(Dispatchers.IO) {
            db.diaryDao().deleteDiary(diary)
        }
    }

    override suspend fun addDiary(diary: Dairy) {

        withContext(Dispatchers.IO) {
            db.diaryDao().addDiary(diary)
        }
    }




}