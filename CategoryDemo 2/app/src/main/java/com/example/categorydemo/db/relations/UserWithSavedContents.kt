package com.example.thought_leadership.data.room_db.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.thought_leadership.data.room_db.SavedContent
import com.example.thought_leadership.data.room_db.User

//data class UserWithSavedContents (
//    @Embedded val user: User,
//    @Relation(
//        parentColumn = "userID",
//        entityColumn = "userID",
//    )
//    val subjects: List<SavedContent>
//)


data class UserWithSavedContents (
    @Embedded val user: User,
    @Relation(
        parentColumn = "userID",
        entityColumn = "userID",
    //    associateBy = Junction(UserSavedContentsCrossRef::class)
    )
    val savedContents: List<SavedContent>
)

//data class SchoolWithStudents(
//    @Embedded val school: School,
//    @Relation(
//        parentColumn = "schoolName",
//        entityColumn = "schoolName"
//    )
//    val students: List<Student>
//)