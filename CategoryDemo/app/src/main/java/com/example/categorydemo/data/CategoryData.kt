package com.example.categorydemo.data

import java.io.Serializable

data class CategoryData(
    val categoryInfo: String,
    var tag:String,
    var isSelected : Boolean
):Serializable