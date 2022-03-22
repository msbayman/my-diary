package com.example.mydiary

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import kotlinx.coroutines.selects.select

@Dao
@Entity
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDiary (diary:Dairy)

    @Delete
    suspend fun deleteDiary (diary: Dairy)

    @Query("SELECT * FROM Dairiesxx ORDER BY time DESC  ")
    suspend fun getALL():List<Dairy>





}