package com.example.tagselectdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.tagselectdemo.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var bottomSheetTagList: TheBottomSheetTagListActivity
    lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
    val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //open bottom sheet
        initBottomSheet()
    }
    fun initBottomSheet(){
        if (!::bottomSheetTagList.isInitialized) {
            bottomSheetTagList = TheBottomSheetTagListActivity()
        }
        showBottonsheet()
    }
    fun showBottonsheet(){
        val mList = ArrayList<String>()
        for (i in 0 .. 50){
            mList.add("$i")
        }

        viewModel.livedataTagList.postValue(mList)

        binding.btn.setOnClickListener {
            if (!bottomSheetTagList.isAdded){
                bottomSheetTagList.show(supportFragmentManager, "TheBottomSheetDialog")
            }
           // bottomSheetTagList.setTag(mList)

        }
    }
}