package com.example.categorydemo.nav

import android.app.Activity
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.categorydemo.data.CategoryData
import com.example.categorydemo.data.DbRepository
import com.example.thought_leadership.data.room_db.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel(context: Activity) : ViewModel() {
    lateinit var categoryInfo:ArrayList<CategoryData>
    lateinit var version : Array<String>
    fun getIndustryList() : Array<String>{
        val version = arrayOf(
            "Aerospace & Defense",
            "Automotive",
            "Banking",
            "Capital Markets",
            "Chemicals",
            "Communications and Media",
            "Consumer Goods and Services",
            "Energy",
            "Health",
            "High Tech",
            "Industrial",
            "Insurance",
            "Life Sciences",
            "Natural Resources",
            "Public Service",
            "Retail",
            "Software and Platform",
            "Travel",
            "US Federal Government",
            "Utilities", //above are industry, below are topic
        )
        return version
    }
    fun getTopicList(): Array<String>{
        val version = arrayOf("Change Management",
            "Cloud",
            "Customer Experience",
            "Cybersecurity",
            "Data & Artificial Intelligence",
            "Digital Engineering and Manufacturing",
            "IT Transformation",
            "Operations",
            "Supply Chain",
            "Sustainability",
            "Tech Ecosystem Platforms")
        return version
    }

    fun setCategoryIndustryInfo() : ArrayList<CategoryData>{
        categoryInfo= ArrayList<CategoryData>()
        val topicVersion = getIndustryList()
        for (item in topicVersion)  {
            val topic = CategoryData(item,"industry",false)
            categoryInfo.add(topic)
        }
        return categoryInfo
    }
    fun setCategoryTopicInfo() : ArrayList<CategoryData>{
        categoryInfo= ArrayList<CategoryData>()
        val topicVersion = getTopicList()
        for (item in topicVersion)  {
            val topic = CategoryData(item,"topic",false)
            categoryInfo.add(topic)
        }
        return categoryInfo
    }

    //db
    private val dbRepository = DbRepository(context)
    fun insertCategoryData(category: Category) = viewModelScope.launch(Dispatchers.IO){
        dbRepository.insertCategoryData(category)
    }
    fun editCategoryData(category: Category) = viewModelScope.launch(Dispatchers.IO){
        dbRepository.editCategoryData(category)
    }
    fun deleteAllCategoryData() = viewModelScope.launch(Dispatchers.IO){
        dbRepository.deleteAllCategoryData()
    }
    fun existsCategory(categoryInfo: String,userID: String) = dbRepository.existsCategory(categoryInfo =categoryInfo, userID = userID )
    fun getAllCategoryData(userID: String) = dbRepository.getAllCategoryData(userID = userID)
    fun getCategoriesOfUser(userID: String) = viewModelScope.launch(Dispatchers.IO){
        dbRepository.getCategoriesOfUser(userID = userID)
    }
    val user = dbRepository.user



}