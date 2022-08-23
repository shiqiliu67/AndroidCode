package com.example.thought_leadership.data.room_db.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.thought_leadership.data.room_db.Category
import com.example.thought_leadership.data.room_db.User


data class UserWithCategories(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userID",
        entityColumn = "userID",
    )
    val categories: List<Category>
)
