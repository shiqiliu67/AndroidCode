package com.example.categorydemo

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.categorydemo.adapter.FollowYourIndustryAdapter
import com.example.categorydemo.adapter.FollowYourIndustryAdapter.Companion.TAG
import com.example.categorydemo.databinding.ActivityFollowYourIndustryBinding
import com.example.categorydemo.nav.CategoryViewModel

class FollowYourIndustryActivity : AppCompatActivity() {
    lateinit var binding: ActivityFollowYourIndustryBinding
    lateinit var mAdapter: FollowYourIndustryAdapter
    var viewModel = CategoryViewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFollowYourIndustryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
       // initToolbar()
    }

    private fun initView() {
        mAdapter = FollowYourIndustryAdapter(
            {//click intent to detailtag activity
                Log.e(TAG, "followingAdapter successful: ")
            }) {
            if(it.isSelected){
                //store in db
                Log.e(TAG, "initView: selected", )
            }
        }
        binding.recyclerViewFollowYourIndustries.apply {
            isNestedScrollingEnabled = false
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@FollowYourIndustryActivity)
            addItemDecoration(MarginItemDecoration(28))
        }
        setData()
    }
    private fun setData(){
//        val mList = viewModel.setCategoryInfo()
//        mAdapter.submitList(mList){
//            Log.e(TAG, "setUpObserver: submit list complete", )
//        }

    }

    fun buttonSetEnable(status: Boolean) {
        binding.nextButtonFollowYourIndustries.isEnabled = status
    }
    @SuppressLint("ResourceType")
    private fun initToolbar() {
        val toolbar = findViewById<View>(R.id.toolbar_follow_your_industries) as Toolbar
        setSupportActionBar(toolbar)
        val actionbar = supportActionBar
        actionbar!!.title = ""
        actionbar.setDisplayShowTitleEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back)

        val states = arrayOf(
            intArrayOf(android.R.attr.state_enabled), // enabled
            intArrayOf(-android.R.attr.state_enabled) // disabled
        )
        val colors = intArrayOf(
            Color.parseColor("#A100FF"), // enabled color  "#0f0"  "#a055f5"
            Color.parseColor("#DCAFFF") // disabled color
        )
        val colorStates = ColorStateList(states,colors)
        binding.nextButtonFollowYourIndustries.apply {
            backgroundTintList = colorStates
            setOnClickListener {
//                for (item in viewModel.setCategoryInfo()){
//                    if (item.isSelected){
//                        Log.e(TAG,"Selected: "+item.categoryInfo)
//                    }
            }
        }
//            var intent = Intent(this, FollowYourTopicsActivity::class.java)
//
//            intent.putExtra("IsRegisteredFollowYourTopics", isRegistered);
//            startActivity(Intent(this@FollowYourIndustryActivity,MainActivity::class.java))
//
//        }

    }


    companion object {
        val TAG = "FollowYourIndustryActivity"
    }
}