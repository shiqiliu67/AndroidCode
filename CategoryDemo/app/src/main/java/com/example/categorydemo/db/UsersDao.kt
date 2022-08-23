package com.example.thought_leadership.data.room_db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.thought_leadership.data.room_db.relations.UserSavedContentWithCategories
import com.example.thought_leadership.data.room_db.relations.UserWithCategories
import com.example.thought_leadership.data.room_db.relations.UserWithLikedContents
import com.example.thought_leadership.data.room_db.relations.UserWithSavedContents

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)
    @Delete
    fun deleteUser(user: User)

    @Query("SELECT EXISTS (SELECT 1 FROM user WHERE userID= :userID )")
    fun existsUser(userID: String): Boolean

    @Query("SELECT * FROM user WHERE userID=:userID")
    fun getCurrentUser(userID: String):User

    @Query("SELECT * FROM user")
    fun getAllUser(): LiveData<List<User>>

    //like
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLikedContent(likedItem: LikedContent)

    @Query("SELECT EXISTS (SELECT 1 FROM likedcontent WHERE userID= :userID and theUrl=:theUrl)")
    fun existsLikedContent(theUrl: String,userID: String): Boolean

    @Delete
    fun deleteLikedContent(item: LikedContent)

    //save
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSavedContent(savedItem: SavedContent)

    @Query("SELECT EXISTS (SELECT 1 FROM savedcontent WHERE userID= :userID and theUrl=:theUrl)")
    fun existsSavedContent(theUrl: String,userID: String): Boolean

    @Delete
    fun deleteSavedContent(item: SavedContent)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSavedContentCategory(savedItem: SavedContentCategory)


    //category
    @Query("SELECT * FROM CATE_GORY_DATA Where userID= :userID")
    fun getAllCategoryData(userID: String): LiveData<List<Category>>

    @Insert
    suspend fun insertCategoryData(category: Category)

    @Update
    suspend fun editCategoryData(category: Category)

    @Query("DELETE FROM CATE_GORY_DATA")
    suspend fun deleteAllCateGoryData()

    @Query("SELECT EXISTS (SELECT 1 FROM CATE_GORY_DATA WHERE userID= :userID and category=:categoryInfo)")
    fun existsCategory(categoryInfo: String,userID: String): Boolean

    @Query("SELECT * FROM SavedContent WHERE userID=:userID")
    fun getAllSavedData(userID: String): LiveData<List<SavedContent>>


    @Query("SELECT * FROM SavedContent WHERE userID=:userID")
    fun getAllSavedData2(userID: String): List<SavedContent>

    @Transaction
    @Query("SELECT * FROM user WHERE userID = :userID")
    suspend fun getSavedContentsOfUser(userID: String):    List<UserWithSavedContents>


    @Transaction
    @Query("SELECT * FROM user WHERE userID = :userID")
    suspend fun getLikedContentsOfUser(userID: String): List<UserWithLikedContents>


    @Transaction
    @Query("SELECT * FROM user WHERE userID = :userID")
    suspend fun getCategoriesOfUser(userID: String): List<UserWithCategories>

    @Transaction
    @Query("SELECT * FROM savedcontent WHERE  userID= :userID and theUrl=:theUrl")
    suspend fun getCategoriesOfUserAndSavedContent(theUrl: String,userID: String): List<UserSavedContentWithCategories>




}