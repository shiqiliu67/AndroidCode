package com.example.thought_leadership.data.room_db.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.thought_leadership.data.room_db.LikedContent
import com.example.thought_leadership.data.room_db.User

data class UserWithLikedContents(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userID",
        entityColumn = "userID",
        //    associateBy = Junction(UserSavedContentsCrossRef::class)
    )
    val likedContents: List<LikedContent>
)
