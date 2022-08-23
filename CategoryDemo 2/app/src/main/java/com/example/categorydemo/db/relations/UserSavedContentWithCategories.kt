package com.example.thought_leadership.data.room_db.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.thought_leadership.data.room_db.SavedContent
import com.example.thought_leadership.data.room_db.SavedContentCategory


data class UserSavedContentWithCategories(
    @Embedded val savedContent: SavedContent,
    @Relation(
        parentColumn = "userID",
        entityColumn = "userID",
    )
//    @Embedded val savedContent: SavedContent,
//    @Relation(
//        parentColumn = "theUrl",
//        entityColumn = "theUrl",
//    )
    val categories: List<SavedContentCategory>
)
