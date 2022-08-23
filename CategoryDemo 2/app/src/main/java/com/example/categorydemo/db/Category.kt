package com.example.thought_leadership.data.room_db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CATE_GORY_DATA")
data class Category(
    @PrimaryKey(autoGenerate = false)
    val category:String,
    var isSelected : Boolean,
    val userID:String

//    @PrimaryKey(autoGenerate = false)
//val theUrl: String,
//val userID:String,
//val title: String,
//val industry: String,
//val growth_topic: String,
//val content_type: String,
//var published_date: String,
//val summary: String,
//val thumbnail_url: String
)
