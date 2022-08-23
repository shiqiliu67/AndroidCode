package com.example.sharedviewmodelapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var selectedUrl = MutableLiveData<String>()
}