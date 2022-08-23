package com.example.thought_leadership.data.room_db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.categorydemo.data.CategoryData
import com.example.thought_leadership.data.room_db.relations.UserSavedContentWithCategories
import com.example.thought_leadership.data.room_db.relations.UserWithCategories
import com.example.thought_leadership.data.room_db.relations.UserWithLikedContents
import com.example.thought_leadership.data.room_db.relations.UserWithSavedContents

@Dao
interface UsersDao {
//    @Insert
//    fun insert(item: User)
//    @Update
//    fun update(item: User)
//    @Delete
//    fun delete(item: User)
//
////    @Query("SELECT * FROM chosenSavedItems " +
////            " WHERE name LIKE :name")
////    fun findItemByName(name:String) : List<SavedItem>
//
//    @Query("SELECT EXISTS (SELECT 1 FROM TheAUsers WHERE `used ID` = :id)")
//    fun exists(id: String): Boolean
//
//
//    @Query("Select * from TheAUsers")
//    fun gelAllItems(): List<User>
//    // @RawQuery  Doesn't import


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)



//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertLikedContent(likedItem: LikedContent)
//
//
//    @Query("SELECT EXISTS (SELECT 1 FROM likedcontent WHERE userID= :userID and theUrl=:theUrl)")
//    fun existsLikedContent(theUrl: String,userID: String): Boolean
//
//    @Delete
//    fun deleteLikedContent(item: LikedContent)
//
//
//
//
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertSavedContent(savedItem: SavedContent)
//
//
//    @Query("SELECT EXISTS (SELECT 1 FROM savedcontent WHERE userID= :userID and theUrl=:theUrl)")
//    fun existsSavedContent(theUrl: String,userID: String): Boolean
//
//    @Delete
//    fun deleteSavedContent(item: SavedContent)
//

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSavedContentCategory(savedItem: SavedContentCategory)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Category)

    @Delete
    suspend fun deleteCategory(category: Category)

    @Transaction
    @Query("SELECT * FROM user WHERE userID = :userID")
    suspend fun getCategoriesOfUser(userID: String): List<UserWithCategories>

    @Query("SELECT * FROM CATE_GORY_DATA")
    fun getAllCategoryData():LiveData<List<Category>>


    @Insert //(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategoryData(category: Category)

    @Update
    suspend fun editCategoryData(category: Category)

    @Query("DELETE FROM CATE_GORY_DATA")
    suspend fun deleteAllCateGoryData()

    @Query("SELECT EXISTS (SELECT 1 FROM cate_gory_data WHERE userID= :userID and category=:categoryInfo)")
    fun existsCategory(categoryInfo: String,userID: String): Boolean


//    @Transaction
//    @Query("SELECT * FROM school WHERE schoolName2 = :schoolName")
//    suspend fun getSavedContentsOfUser(schoolName: String): List<UserWithSavedContents>

//    @Transaction
//    @Query("SELECT * FROM user WHERE userID = :userID")
//    suspend fun getSavedContentsOfUser(userID: String): List<UserWithSavedContents>
//
//
//    @Transaction
//    @Query("SELECT * FROM user WHERE userID = :userID")
//    suspend fun getLikedContentsOfUser(userID: String): List<UserWithLikedContents>
//
//



//    @Transaction
//    @Query("SELECT * FROM savedcontent WHERE userID = :userID and theUrl= theUrl")
//    suspend fun getCategoriesOfUserAndSavedContent(userID: String,theUrl: String): List<UserSavedContentWithCategories>

//    @Transaction
//    @Query("SELECT * FROM savedcontent WHERE  userID= :userID and theUrl=:theUrl")
//    suspend fun getCategoriesOfUserAndSavedContent(theUrl: String,userID: String): List<UserSavedContentWithCategories>
//



}