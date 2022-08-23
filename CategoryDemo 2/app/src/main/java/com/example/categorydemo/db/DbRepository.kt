package com.example.categorydemo.db

import android.content.Context
import com.example.thought_leadership.data.room_db.Category
import com.example.thought_leadership.data.room_db.SavedContentCategory
import com.example.thought_leadership.data.room_db.User
import com.example.thought_leadership.data.room_db.UsersDataBase

class DbRepository (context:Context){
    private val getDataBase = UsersDataBase.getInstance(context)
    suspend fun insertUser(user: User)=getDataBase.userDao.insertUser(user)
    suspend fun insertSavedContentCategory(savedItem: SavedContentCategory) = getDataBase.userDao.insertSavedContentCategory(savedItem)
    suspend fun insertCategoryData(category: Category) = getDataBase.userDao.insertCategoryData(category)
    suspend fun editCategoryData(category: Category) = getDataBase.userDao.editCategoryData(category)
    suspend fun deleteAllCategoryData() = getDataBase.userDao.deleteAllCateGoryData()
    fun existsCategory(categoryInfo: String,userID: String) = getDataBase.userDao.existsCategory(categoryInfo, userID)
    fun getAllCategoryData() = getDataBase.userDao.getAllCategoryData()

     val user = UsersDataBase.provisionalUserName
}
