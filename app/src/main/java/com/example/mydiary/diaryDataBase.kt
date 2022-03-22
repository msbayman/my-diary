package com.example.mydiary

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

private const val DATABASE_NAME="Dairiesxx"

@Database(entities = [Dairy::class], version = 1, exportSchema = false)
abstract class diaryDataBase: RoomDatabase() {

    abstract fun diaryDao():Dao

    companion object {
        @Volatile private var instance: diaryDataBase? = null


       fun getInstance(context: Context):diaryDataBase{
           return instance?: kotlin.synchronized(Any()){
               instance?: buildDatabase(context).also { instance=it }
           }
       }

        private fun buildDatabase(context: Context): diaryDataBase {
            return Room.databaseBuilder(
                context.applicationContext,diaryDataBase::class.java, DATABASE_NAME
            ).build()

        }


    }





}