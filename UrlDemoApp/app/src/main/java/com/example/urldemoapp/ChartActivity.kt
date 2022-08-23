package com.example.urldemoapp

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.urldemoapp.databinding.ActivityChartBinding

class ChartActivity : AppCompatActivity() {
    lateinit var binding: ActivityChartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolbar()
        initView()
    }
    private fun initView(){
        try {
            binding.tvChartTag.text = "Charts"
            val url = intent.getStringExtra("url").toString()
            Log.e(TAG, "initView: url is $url", )

            if (url != null) {
                setWebUrl(url)
            } else {
                Toast.makeText(baseContext, "Oops,cannot find the url", Toast.LENGTH_LONG).show()
            }
        }
        catch (e:Exception){
            Toast.makeText(baseContext,"Oops, there is an error: ${e.message}",Toast.LENGTH_LONG).show()
        }

    }
    private fun setWebUrl(url:String){
        val settings: WebSettings = binding.chartWebview.settings
        settings.let {
            it.setRenderPriority(WebSettings.RenderPriority.HIGH)
            it.javaScriptEnabled = true
            it.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
            it.domStorageEnabled = true
            it.loadWithOverviewMode = true
            it.useWideViewPort = true
        }
        binding.chartWebview.loadUrl(url)
        Log.e(TAG, "setWebUrl: $url", )
        binding.chartWebview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                Log.e(TAG, "shouldOverrideUrlLoading: ", )
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.layoutChart.requestLayout()
                Log.e(TAG, "onPageFinished: ", )
            }


            override fun onLoadResource(view: WebView?, url: String?) {
                super.onLoadResource(view, url)
                Log.e(TAG, "onLoadResource: ", )
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                Log.e(TAG, "onPageStarted: ", )
            }
        }
    }

    private fun initToolbar() {
        val toolbar = findViewById<View>(R.id.toolbar_return) as Toolbar
        setSupportActionBar(toolbar)
        val actionbar = supportActionBar
        actionbar!!.title = ""
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setHomeAsUpIndicator(R.drawable.ic_back)
    }
    //handle back pressed button to previous
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        //startActivity(Intent(this,MainActivity::class.java))
        return true
    }
    companion object{
        private val TAG = "ChartActivity"
    }
}