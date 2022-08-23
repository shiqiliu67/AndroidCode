package com.example.categorydemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.categorydemo.databinding.ActivityMainBinding
import com.example.categorydemo.nav.CategoryFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setNavController()
    }
    private fun setNavController(){
        val navController = (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController
        bottomNavigationView.setupWithNavController(navController)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        var check =""
        intent?.let {
            check = it.getStringExtra("nav").toString()
            Log.e(TAG, "onNewIntent:$check" )
        }
        when(check){
            "discover"->{}
            "myfeed"->{}
            "topic"->{
                binding.bottomNavigationView.selectedItemId = R.id.topic_nav
            }
            else->{}
        }
    }
    companion object{
        val TAG = "MainActivity"
    }
}