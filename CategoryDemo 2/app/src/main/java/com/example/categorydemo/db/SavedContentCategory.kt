package com.example.thought_leadership.data.room_db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SavedContentCategory(
    @PrimaryKey(autoGenerate = false)
    val category: String,
    val userID: String,
    val theUrl: String
)
