package com.example.sharedviewmodelapplication

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProvider
import com.example.sharedviewmodelapplication.databinding.FragmentWebviewBinding

class WebviewFragment : Fragment() {
    lateinit var binding: FragmentWebviewBinding

    //    val viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentWebviewBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initView() {
        binding.webview.settings.let {
            it.javaScriptEnabled = true
        }
        binding.webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }

        val viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        viewModel.selectedUrl.observe(viewLifecycleOwner) {
            Log.e(TAG, "initView: load url! url is$it")
            binding.webview.loadUrl(it)
        }
    }

    companion object {
        private const val TAG = "WebviewFrg"
    }
}