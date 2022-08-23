package com.example.sharedviewmodelapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.sharedviewmodelapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel : MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }
    private fun initView(){
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.selectedUrl
        supportFragmentManager.beginTransaction().add(R.id.fragment1,WebviewFragment()).commit()
        supportFragmentManager.beginTransaction().add(R.id.fragment2,UrlLinkFragment()).commit()
    }
}