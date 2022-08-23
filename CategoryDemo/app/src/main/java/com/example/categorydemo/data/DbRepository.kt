package com.example.categorydemo.data

import android.content.Context
import com.example.thought_leadership.data.room_db.*


class DbRepository (context: Context){
    private val getDataBase = UsersDataBase.getInstance(context)
    //user
    suspend fun insertUser(user: User)=getDataBase.userDao.insertUser(user)
    fun existsUser(userID: String) = getDataBase.userDao.existsUser(userID)
    fun deleteUser(user: User) = getDataBase.userDao.deleteUser(user)
    fun getCurrentUser(userID: String) = getDataBase.userDao.getCurrentUser(userID)
    fun getAllUsers() =getDataBase.userDao.getAllUser()
    //saved content
    suspend fun insertSavedContent(savedItem: SavedContent) = getDataBase.userDao.insertSavedContent(savedItem)
    suspend fun insertSavedContentCategory(savedItem: SavedContentCategory) = getDataBase.userDao.insertSavedContentCategory(savedItem)
    fun existsSavedContent(theUrl: String,userID: String) = getDataBase.userDao.existsSavedContent(theUrl, userID)
    fun deleteSavedContent(item: SavedContent) = getDataBase.userDao.deleteSavedContent(item)
    fun getAllSavedData(userID: String)= getDataBase.userDao.getAllSavedData(userID)
    fun getAllSavedData2(userID: String)= getDataBase.userDao.getAllSavedData2(userID)

    //liked content
    suspend fun insertLikedContent(likedItem: LikedContent) = getDataBase.userDao.insertLikedContent(likedItem)
    fun existsLikedContent(theUrl: String,userID: String) = getDataBase.userDao.existsLikedContent(theUrl, userID)
    fun deleteLikedContent(item: LikedContent) =getDataBase.userDao.deleteLikedContent(item)

    //category
    suspend fun insertCategoryData(category: Category) = getDataBase.userDao.insertCategoryData(category)
    suspend fun editCategoryData(category: Category) = getDataBase.userDao.editCategoryData(category)
    suspend fun deleteAllCategoryData() = getDataBase.userDao.deleteAllCateGoryData()
    fun existsCategory(categoryInfo: String,userID: String) = getDataBase.userDao.existsCategory(categoryInfo, userID)
    fun getAllCategoryData(userID: String) = getDataBase.userDao.getAllCategoryData(userID = userID)

    //relationship
    suspend fun getSavedContentsOfUser(userID: String) = getDataBase.userDao.getSavedContentsOfUser(userID)
    suspend fun getLikedContentsOfUser(userID: String) = getDataBase.userDao.getLikedContentsOfUser(userID)
    suspend fun getCategoriesOfUser(userID: String) = getDataBase.userDao.getCategoriesOfUser(userID)
    suspend fun getCategoriesOfUserAndSavedContent(theUrl: String,userID: String) = getDataBase.userDao.getCategoriesOfUserAndSavedContent(theUrl, userID)

    val user = UsersDataBase.provisionalUserName
}
