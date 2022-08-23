package com.example.firstproject.util

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import kotlin.math.abs

abstract class BaseFragment<VB:ViewBinding>(@LayoutRes contentLayoutId:Int):Fragment(contentLayoutId) {
    protected abstract val binding:VB
    protected abstract fun initView()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 沉浸式状态栏
//        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
//            val statusBar = insets.getInsets(WindowInsetsCompat.Type.statusBars())
//            val height = abs(statusBar.top - statusBar.bottom)
//            v.updatePadding(top = height)
//            insets
//        }
        initView()
    }
}