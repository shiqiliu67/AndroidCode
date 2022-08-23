package com.example.tagselectdemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel():ViewModel() {
    val livedataTagList = MutableLiveData<ArrayList<String>>()


}