package com.example.firstproject

import androidx.navigation.fragment.findNavController
import com.example.firstproject.databinding.FragmentExploreBinding
import com.example.firstproject.util.BaseFragment
import com.example.firstproject.util.bindView


class ExploreFragment : BaseFragment<FragmentExploreBinding>(R.layout.fragment_explore) {
    override val binding: FragmentExploreBinding by bindView()

    override fun initView() {
        binding.root.setOnClickListener {
            findNavController().navigate(R.id.testFragment)
        }
    }

}