package com.example.thought_leadership.data.room_db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CATE_GORY_DATA")
data class Category(
    @PrimaryKey(autoGenerate = false)
    val category:String,
    var isSelected : Boolean,
    var tag:String,
    val userID:String
)

