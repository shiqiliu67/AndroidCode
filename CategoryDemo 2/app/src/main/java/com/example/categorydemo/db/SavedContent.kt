package com.example.thought_leadership.data.room_db

import androidx.room.Entity
import androidx.room.PrimaryKey


//
@Entity
data class SavedContent (

//    @PrimaryKey(autoGenerate = false)
//    val studentName: String,
//    val semester: Int,
//    val userID: String
    @PrimaryKey(autoGenerate = false)
    val theUrl: String,
    val userID:String,
    val title: String,
    val content_type: String,
    var published_date: String,
    val summary: String,
    val thumbnail_url: String
)