package com.example.mydiary

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Dairiesxx")
public data class Dairy(



    val text: String,
    val title: String,
    @PrimaryKey(autoGenerate = false)
    val time: String
)
{
    

}