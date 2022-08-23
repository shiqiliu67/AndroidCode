package com.example.categorydemo

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.categorydemo.data.CategoryData
import com.example.categorydemo.db.DbRepository
import com.example.thought_leadership.data.room_db.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel (context: Context): ViewModel() {
    lateinit var categoryInfo:ArrayList<CategoryData>
    lateinit var version : Array<String>
    fun getList() : Array<String>{
        var version = arrayOf(
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
            "Utilities",
            "Change Management",
            "Cloud",
            "Customer Experience",
            "Cybersecurity",
            "Data & Artificial Intelligence",
            "Digital Engineering and Manufacturing",
            "IT Transformation",
            "Operations",
            "Supply Chain",
            "Sustainability",
            "Tech Ecosystem Platforms"
        )
        return version
    }

    fun setCategoryInfo() : ArrayList<CategoryData>{
        categoryInfo= ArrayList<CategoryData>()
        var topicVersion = getList()
        for (item in topicVersion)  {
//            var indD = CategoryData()
//            indD.setCategoryInfo(item)
            var topic = CategoryData(item,false)
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

    fun getAllCategoryData() = dbRepository.getAllCategoryData()

    val user = dbRepository.user

}