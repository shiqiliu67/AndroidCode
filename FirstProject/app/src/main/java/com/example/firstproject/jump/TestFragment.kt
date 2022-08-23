package com.example.firstproject.jump

import androidx.fragment.app.Fragment
import com.example.firstproject.R
import com.example.firstproject.databinding.FragmentTestBinding
import com.example.firstproject.util.BaseFragment
import com.example.firstproject.util.bindView


class TestFragment : BaseFragment<FragmentTestBinding>(R.layout.fragment_test) {
    override val binding: FragmentTestBinding by bindView()

    override fun initView() {

    }

}