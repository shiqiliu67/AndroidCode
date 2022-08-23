package com.example.firstproject


import com.example.firstproject.databinding.FragmentMeBinding
import com.example.firstproject.util.BaseFragment
import com.example.firstproject.util.bindView


class MeFragment : BaseFragment<FragmentMeBinding>(R.layout.fragment_me) {
    override val binding: FragmentMeBinding by bindView()

    override fun initView() {

    }

}