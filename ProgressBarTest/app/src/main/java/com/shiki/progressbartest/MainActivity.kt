package com.shiki.progressbartest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    private lateinit var progressBar:ProgressBar
    private lateinit var displayBtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

    }

    private fun initView() {
        progressBar = findViewById<ProgressBar>(R.id.progressBar)
        displayBtn = findViewById<Button>(R.id.display_progress_btn).apply {
            setOnClickListener {
               // progressBar.isVisible = !progressBar.isVisible
                //show the dialog
                val dialog = LoadingDialog(this@MainActivity)
                    dialog.startLoadingDialog()
            }
        }
    }
}