package com.example.categorydemo.data

import java.io.Serializable

//class CategoryData {
//    private var categoryInfo: String? = null
//    private var isSelected = false
//
//    fun getCategoryInfo(): String? {
//        return categoryInfo
//    }
//
//    fun setCategoryInfo(categoryInfo: String?) {
//        this.categoryInfo = categoryInfo
//    }
//
//
//    fun isSelected(): Boolean {
//        return isSelected
//    }
//
//    fun setSelected(selected: Boolean) {
//        isSelected = selected
//    }
//}

data class CategoryData(
    val categoryInfo: String,
    var isSelected : Boolean
):Serializable