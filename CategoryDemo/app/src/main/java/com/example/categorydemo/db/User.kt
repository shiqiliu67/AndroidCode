package com.example.thought_leadership.data.room_db

import androidx.room.*



@Entity
data class User (
    @PrimaryKey(autoGenerate = false)
    val userID: String,
    val authority:String,
    val isToken:String,
    val email:String,
    val username:String,
    val expiresOn:String


)


//@Entity(tableName = "TheAUsers")
//data class User (
//
//    @PrimaryKey
//    @ColumnInfo(name = "used ID")
//    val userID:String,
//    @ColumnInfo(name = "Authority")
//    val authority:String,
//    @ColumnInfo(name = "Is Token")
//    val isToken:String,
//    @ColumnInfo(name = "Email")
//    val email:String,
//    @ColumnInfo(name = "User Name")
//    val username:String,
//    @ColumnInfo(name = "Expires On")
//    val expiresOn:String
//
//
//)
//
//
//@Entity
//data class SavedContent (
//
//    @PrimaryKey
//    @ColumnInfo(name = "URL")
//    val url: String,
//    @ColumnInfo(name = "Title")
//    val title: String,
//    @ColumnInfo(name = "Industry")
//    val industry: String,
//    @ColumnInfo(name = "Grouth topic")
//    val growth_topic: String,
//    @ColumnInfo(name = "Content type")
//    val content_type: String,
//    @ColumnInfo(name = "Published date")
//    var published_date: String,
//    @ColumnInfo(name = "Summary")
//    val summary: String,
//    @ColumnInfo(name = "Thumbnail URL")
//    val thumbnail_url: String
//)


//
//
//@Entity(tableName = "TheAUsers")
//data class UserWithSaveContents (
//    @Embedded
//    var user: User? = null,
//
//    @Relation(parentColumn = "userID", entityColumn = "url", entity = SavedContent::class)
//    var savecontents: List<SavedContent>? = null
//)
//
//
//
//
//
//
//
//
//
//@Entity
//data class TestModel (
//    @PrimaryKey
//    var id:Int = 0,
//    var name: String? = null,
//    var age: String? = null
//)
//
//@Entity
//data class Book (
//    @PrimaryKey
//    var id:Int // Book id
//            = 0,
//    var testModelId:Int // TestModel id
//            = 0,
//    var title: String? = null,
//    var ISBN:Int = 0
//    )
//
//data class TestModelWithBooks (
//    @Embedded
//    var testModel: TestModel? = null,
//
//    @Relation(parentColumn = "id", entityColumn = "testModelId", entity = Book::class)
//    var books: List<Book>? = null
//    )
//
//
//
//
//



